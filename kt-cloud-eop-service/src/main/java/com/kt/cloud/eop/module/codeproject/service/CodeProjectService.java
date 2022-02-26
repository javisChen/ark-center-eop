package com.kt.cloud.eop.module.codeproject.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kt.cloud.eop.module.codeproject.dto.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.module.codeproject.dto.query.request.CodeProjectListQueryReq;
import com.kt.cloud.eop.module.codeproject.dto.query.response.CodeProjectCreateRespDto;
import com.kt.cloud.eop.module.codeproject.dto.query.response.CodeProjectInfoRespDto;
import com.kt.cloud.eop.module.codeproject.dto.query.response.CodeProjectListRespDto;
import com.kt.cloud.eop.dao.entity.ProjectBasic;
import com.kt.cloud.eop.dao.service.IProjectBasicService;
import com.kt.cloud.eop.module.codeproject.convertor.CodeProjectConvertor;
import com.kt.cloud.eop.module.codeproject.generate.project.ProjectGenerator;
import com.kt.cloud.eop.module.codeproject.generate.work.GitPushTask;
import com.kt.component.common.ParamsChecker;
import com.kt.component.exception.ExceptionFactory;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeProjectCreateRespDto createCodeProject(CodeProjectCreateCmd codeProjectCmd) {
        ProjectGenerator projectGenerator = getProjectGenerator(codeProjectCmd);
        ParamsChecker.throwIfIsNull(projectGenerator, ExceptionFactory.userException("选择的脚手架类型不存在"));

        // 转换工程扩展属性
        Map<String, Object> params = convertToMap(codeProjectCmd.getExtProperties());

        // 执行工程生成
        File codeProject = projectGenerator.generator(params);

        // 持久化到存储
        ProjectBasic projectBasic = saveProject(codeProjectCmd);

        gitPushTask.push(projectBasic.getId(), codeProjectCmd, codeProject);
        return new CodeProjectCreateRespDto();

    }

    private ProjectBasic saveProject(CodeProjectCreateCmd codeProjectCmd) {
        ProjectBasic projectBasic = CodeProjectConvertor.convertToProjectBasic(codeProjectCmd);
        iProjectBasicService.save(projectBasic);
        return projectBasic;
    }

    @Override
    public IPage<CodeProjectListRespDto> pageListCodeProject(CodeProjectListQueryReq query) {
        LambdaQueryWrapper<ProjectBasic> qw = new LambdaQueryWrapper<>();
        qw.like(StringUtils.isNotEmpty(query.getProjectName()), ProjectBasic::getName, query.getProjectName())
        .orderByDesc(ProjectBasic::getGmtCreate);
        return iProjectBasicService.page(new Page<>(query.getCurrent(), query.getSize()), qw)
                .convert(CodeProjectConvertor::convertToCodeProjectListVo);
    }

    @Override
    public CodeProjectInfoRespDto getCodeProjectInfo(Long codeProjectId) {
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
