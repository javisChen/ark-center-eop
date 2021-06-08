package com.kt.cloud.cop.module.codeproject.service;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.enums.ReposSourceEnums;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectCreateVO;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectInfoVO;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVO;
import com.kt.cloud.cop.dao.entity.ProjectBasic;
import com.kt.cloud.cop.dao.service.IProjectBasicService;
import com.kt.cloud.cop.infrastructure.generate.project.ProjectGenerator;
import com.kt.cloud.cop.module.codeproject.convertor.CodeProjectConvertor;
import com.kt.cloud.cop.module.git.GitCreate;
import com.kt.cloud.cop.module.git.GitReposInfo;
import com.kt.cloud.cop.module.git.GitService;
import com.kt.component.dto.PagingQuery;
import com.kt.component.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Map;

@Service
public class CodeProjectService implements ICodeProjectService {

    @Autowired
    private GitService gitService;

    @Autowired
    private Map<String, ProjectGenerator> projectGeneratorMap;

    @Autowired
    private IProjectBasicService iProjectBasicService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeProjectCreateVO createCodeProject(CodeProjectCreateCmd codeProjectCmd) {
        ProjectGenerator projectGenerator = getProjectGenerator(codeProjectCmd);
        if (projectGenerator == null) {
            throw new BizException("不存在该类型的工程");
        }

        // 转换工程扩展属性
        Map<String, Object> params = convertToMap(codeProjectCmd.getExtProperties());

        // 执行工程生成
        File codeProject = projectGenerator.generator(params);

        // 尝试生成Git仓库
        String gitReposUrl = attemptCreateGitRepos(codeProjectCmd, codeProject);

        // 判断生成工程后是否删除临时目录
        attemptDeleteTempFileAfterGen(codeProjectCmd, codeProject);

        // 持久化到存储
        saveProject(codeProjectCmd, gitReposUrl);

        return new CodeProjectCreateVO(gitReposUrl);

    }

    private void attemptDeleteTempFileAfterGen(CodeProjectCreateCmd codeProjectCmd, File codeProject) {
        if (codeProjectCmd.getDeleteTempFileAfterGen()) {
            FileUtil.del(codeProject);
        }
    }

    private String attemptCreateGitRepos(CodeProjectCreateCmd codeProjectCmd, File codeProject) {
        String gitReposUrl = "";
        if (codeProjectCmd.getReposSource().equals(ReposSourceEnums.CREATE_NEW.getValue())) {
            GitCreate gitCreate = CodeProjectConvertor.convertToGitCreate(codeProjectCmd);
            GitReposInfo gitReposInfo = gitService.createRepository(gitCreate);
            gitReposUrl = gitReposInfo.getReposUrl();
            gitService.intiAndPushToRepos(codeProject, gitReposInfo);
        }
        return gitReposUrl;
    }

    private void saveProject(CodeProjectCreateCmd codeProjectCmd, String gitReposUrl) {
        ProjectBasic projectBasic = CodeProjectConvertor.convertToProjectBasic(codeProjectCmd, gitReposUrl);
        iProjectBasicService.save(projectBasic);
    }

    @Override
    public IPage<CodeProjectListVO> pageListCodeProject(PagingQuery pagingQuery) {
        LambdaQueryWrapper<ProjectBasic> qw = new LambdaQueryWrapper<>();
        qw.orderByDesc(ProjectBasic::getGmtCreate);
        return iProjectBasicService.page(new Page<>(pagingQuery.getCurrent(), pagingQuery.getSize()), qw)
                .convert(CodeProjectConvertor::convertToCodeProjectListVo);
    }

    @Override
    public CodeProjectInfoVO getCodeProjectInfo(Long codeProjectId) {
        ProjectBasic projectBasic = iProjectBasicService.getById(codeProjectId);
        return CodeProjectConvertor.convertToCodeProjectInfoVO(projectBasic);
    }

    private Map<String, Object> convertToMap(String extProperties) {
        return JSONObject.parseObject(extProperties, new TypeReference<Map<String, Object>>(){});
    }

    private ProjectGenerator getProjectGenerator(CodeProjectCreateCmd cmd) {
        return projectGeneratorMap.get(ProjectBasic.Scaffold.getText(cmd.getScaffold()) + "ProjectGenerator");
    }
}
