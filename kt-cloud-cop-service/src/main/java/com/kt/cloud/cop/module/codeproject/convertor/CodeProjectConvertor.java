package com.kt.cloud.cop.module.codeproject.convertor;

import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.dao.entity.ProjectBasic;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVo;
import com.kt.cloud.cop.module.git.GitCreate;

public class CodeProjectConvertor {

    public static GitCreate convertToGitCreate(CodeProjectCreateCmd cmd) {
        GitCreate gitCreate = new GitCreate();
        gitCreate.setName(cmd.getCode());
        gitCreate.setDescription(cmd.getDescription());
        return gitCreate;
    }

    public static CodeProjectListVo convertToCodeProjectListVo(ProjectBasic projectBasic) {
        CodeProjectListVo vo = new CodeProjectListVo();
        vo.setName(projectBasic.getName());
        vo.setCode(projectBasic.getCode());
        vo.setDescription(projectBasic.getDescription());
        vo.setType(ProjectBasic.Type.getText(projectBasic.getType()));
        vo.setScaffold(ProjectBasic.Scaffold.getText(projectBasic.getScaffold()));
        vo.setGitReposUrl(projectBasic.getGitReposUrl());
        vo.setCreateTime(projectBasic.getGmtCreate());
        return vo;
    }

    public static ProjectBasic convertToProjectBasic(CodeProjectCreateCmd codeProjectCmd, String gitReposUrl) {
        ProjectBasic entity = new ProjectBasic();
        entity.setName(codeProjectCmd.getName());
        entity.setCode(codeProjectCmd.getCode());
        entity.setDescription(codeProjectCmd.getDescription());
        entity.setGitReposUrl(gitReposUrl);
        return entity;
    }
}
