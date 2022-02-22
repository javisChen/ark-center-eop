package com.kt.cloud.eop.module.codeproject.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kt.cloud.eop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.client.codeproject.query.CodeProjectListQuery;
import com.kt.cloud.eop.client.codeproject.vo.CodeProjectCreateVO;
import com.kt.cloud.eop.client.codeproject.vo.CodeProjectInfoVO;
import com.kt.cloud.eop.client.codeproject.vo.CodeProjectListVO;
import com.kt.cloud.eop.dao.entity.ProjectBasic;
import com.kt.cloud.eop.dao.service.IProjectBasicService;
import com.kt.cloud.eop.module.codeproject.CodeProjectValidator;
import com.kt.cloud.eop.module.codeproject.convertor.CodeProjectConvertor;
import com.kt.cloud.eop.module.codeproject.generate.project.ProjectGenerator;
import com.kt.cloud.eop.module.codeproject.generate.work.GitPushTask;
import com.kt.component.cache.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Map;

@Service
public class CodeProjectService implements ICodeProjectService {

    @Autowired
    private Map<String, ProjectGenerator> projectGeneratorMap;
    @Autowired
    private IProjectBasicService iProjectBasicService;
    @Autowired
    private GitPushTask gitPushTask;
    @Autowired
    private RedisService redisService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeProjectCreateVO createCodeProject(CodeProjectCreateCmd codeProjectCmd) {
        ProjectGenerator projectGenerator = getProjectGenerator(codeProjectCmd);
        CodeProjectValidator.isNull(projectGenerator, "不存在该类型的工程");

        // 转换工程扩展属性
        Map<String, Object> params = convertToMap(codeProjectCmd.getExtProperties());

        // 执行工程生成
        File codeProject = projectGenerator.generator(params);

        // 持久化到存储
        ProjectBasic projectBasic = saveProject(codeProjectCmd);

        redisService.set("redis", "66666");
        gitPushTask.push(projectBasic.getId(), codeProjectCmd, codeProject);
        return new CodeProjectCreateVO();

    }

    private ProjectBasic saveProject(CodeProjectCreateCmd codeProjectCmd) {
        ProjectBasic projectBasic = CodeProjectConvertor.convertToProjectBasic(codeProjectCmd);
        iProjectBasicService.save(projectBasic);
        return projectBasic;
    }

    @Override
    public IPage<CodeProjectListVO> pageListCodeProject(CodeProjectListQuery query) {
        LambdaQueryWrapper<ProjectBasic> qw = new LambdaQueryWrapper<>();
        qw.like(ProjectBasic::getName, query.getProjectName())
        .orderByDesc(ProjectBasic::getGmtCreate);
        return iProjectBasicService.page(new Page<>(query.getCurrent(), query.getSize()), qw)
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
