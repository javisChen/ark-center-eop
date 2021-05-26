package com.kt.cloud.cop.module.codeproject.service;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kt.cloud.cop.dao.entity.ProjectBasic;
import com.kt.cloud.cop.dao.service.IProjectBasicService;
import com.kt.cloud.cop.module.codeproject.vo.CodeProjectGenVo;
import com.kt.cloud.cop.module.codeproject.vo.CodeProjectListVo;
import com.kt.cloud.cop.module.git.GitCreate;
import com.kt.cloud.cop.module.git.GitReposInfo;
import com.kt.cloud.cop.module.git.GitService;
import com.kt.cloud.cop.infrastructure.generate.project.ProjectGenerator;
import com.kt.cloud.cop.module.codeproject.GenCodeProjectDTO;
import com.kt.cloud.cop.module.codeproject.convertor.CodeProjectConvertor;
import com.kt.component.dto.PagingDTO;
import com.kt.component.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CodeProjectGenVo createCodeProject(GenCodeProjectDTO genCodeProjectDTO) {
        ProjectGenerator projectGenerator = getProjectGenerator(genCodeProjectDTO);
        if (projectGenerator == null) {
            throw new BizException("不存在该类型的工程");
        }
        Map<String, Object> params = convertToMap(genCodeProjectDTO.getExtProperties());
        File codeProject = projectGenerator.generator(params);

        GitCreate gitCreate = CodeProjectConvertor.convertForCreate(genCodeProjectDTO);

        String gitReposUrl = "";
        if (genCodeProjectDTO.getCreateGitRepos()) {
            GitReposInfo gitReposInfo = gitService.createRepository(gitCreate);
            gitReposUrl = gitReposInfo.getReposUrl();
            gitService.intiAndPushToRepos(codeProject, gitReposInfo);
        }

        if (genCodeProjectDTO.getDeleteTempFileAfterGen()) {
            FileUtil.del(codeProject);
        }

        ProjectBasic entity = new ProjectBasic();
        entity.setName("");
        entity.setDescription("");
        entity.setGitReposUrl(gitReposUrl);
        iProjectBasicService.save(entity);

        return new CodeProjectGenVo(gitReposUrl);

    }

    @Override
    public IPage<CodeProjectListVo> listVos(PagingDTO pagingDTO) {
        return iProjectBasicService.page(new Page<>(pagingDTO.getCurrent(), pagingDTO.getSize()))
                .convert(CodeProjectConvertor::convertToCodeProjectListVo);
    }

    private Map<String, Object> convertToMap(String extProperties) {
        return JSONObject.parseObject(extProperties, new TypeReference<Map<String, Object>>(){});
    }

    private ProjectGenerator getProjectGenerator(GenCodeProjectDTO genCodeProjectDTO) {
        return projectGeneratorMap.get(genCodeProjectDTO.getType() + "ProjectGenerator");
    }
}
