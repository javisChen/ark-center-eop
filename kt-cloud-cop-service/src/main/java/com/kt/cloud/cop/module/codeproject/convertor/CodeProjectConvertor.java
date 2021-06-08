package com.kt.cloud.cop.module.codeproject.convertor;

import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.dao.entity.ProjectBasic;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVO1;
import com.kt.cloud.cop.module.git.GitCreate;

public class CodeProjectConvertor {

    public static GitCreate convertToGitCreate(CodeProjectCreateCmd cmd) {
        GitCreate gitCreate = new GitCreate();
        gitCreate.setName(cmd.getCode());
        gitCreate.setDescription(cmd.getDescription());
        return gitCreate;
    }

    public static CodeProjectListVO1 convertToCodeProjectListVo(ProjectBasic projectBasic) {
        CodeProjectListVO1 vo = new CodeProjectListVO1();
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

    public static ProjectBasic convertToProjectBasic(CodeProjectCreateCmd cmd, String gitReposUrl) {
        ProjectBasic entity = new ProjectBasic();
        entity.setName(cmd.getName());
        entity.setCode(cmd.getCode());
        entity.setDescription(cmd.getDescription());
        entity.setGitReposUrl(gitReposUrl);
        entity.setExtProperties(cmd.getExtProperties());
        return entity;
    }
}
