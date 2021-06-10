package com.kt.cloud.cop.module.codeproject.convertor;

import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectInfoVO;
import com.kt.cloud.cop.dao.entity.ProjectBasic;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVO;
import com.kt.cloud.cop.module.git.GitCreate;

public class CodeProjectConvertor {

    public static GitCreate convertToGitCreate(CodeProjectCreateCmd cmd) {
        GitCreate gitCreate = new GitCreate();
        gitCreate.setName(cmd.getCode());
        gitCreate.setDescription(cmd.getDescription());
        return gitCreate;
    }

    public static CodeProjectListVO convertToCodeProjectListVo(ProjectBasic projectBasic) {
        CodeProjectListVO vo = new CodeProjectListVO();
        vo.setId(projectBasic.getId());
        vo.setName(projectBasic.getName());
        vo.setCode(projectBasic.getCode());
        vo.setDescription(projectBasic.getDescription());
        vo.setType(ProjectBasic.Type.getText(projectBasic.getType()));
        vo.setScaffold(ProjectBasic.Scaffold.getText(projectBasic.getScaffold()));
        vo.setGitReposUrl(projectBasic.getGitReposUrl());
        vo.setCreateTime(projectBasic.getGmtCreate());
        return vo;
    }

    public static ProjectBasic convertToProjectBasic(CodeProjectCreateCmd cmd) {
        ProjectBasic entity = new ProjectBasic();
        entity.setName(cmd.getName());
        entity.setCode(cmd.getCode());
        entity.setDescription(cmd.getDescription());
        entity.setExtProperties(cmd.getExtProperties());
        return entity;
    }

    public static CodeProjectInfoVO convertToCodeProjectInfoVO(ProjectBasic projectBasic) {
        CodeProjectInfoVO vo = new CodeProjectInfoVO();
        vo.setId(projectBasic.getId());
        vo.setName(projectBasic.getName());
        vo.setCode(projectBasic.getCode());
        vo.setDescription(projectBasic.getDescription());
        vo.setType(ProjectBasic.Type.getText(projectBasic.getType()));
        vo.setScaffold(ProjectBasic.Scaffold.getText(projectBasic.getScaffold()));
        vo.setGitReposUrl(projectBasic.getGitReposUrl());
        return vo;
    }
}
