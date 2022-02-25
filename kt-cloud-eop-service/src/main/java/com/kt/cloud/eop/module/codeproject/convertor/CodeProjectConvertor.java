package com.kt.cloud.eop.module.codeproject.convertor;

import com.kt.cloud.eop.api.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.api.codeproject.query.response.CodeProjectInfoRespDto;
import com.kt.cloud.eop.api.codeproject.query.response.CodeProjectListRespDto;
import com.kt.cloud.eop.dao.entity.ProjectBasic;
import com.kt.cloud.eop.module.git.GitCreate;

public class CodeProjectConvertor {

    public static GitCreate convertToGitCreate(CodeProjectCreateCmd cmd) {
        GitCreate gitCreate = new GitCreate();
        gitCreate.setName(cmd.getCode());
        gitCreate.setDescription(cmd.getDescription());
        return gitCreate;
    }

    public static CodeProjectListRespDto convertToCodeProjectListVo(ProjectBasic projectBasic) {
        CodeProjectListRespDto vo = new CodeProjectListRespDto();
        vo.setId(projectBasic.getId());
        vo.setName(projectBasic.getName());
        vo.setCode(projectBasic.getCode());
        vo.setDescription(projectBasic.getDescription());
        vo.setType(ProjectBasic.Type.getText(projectBasic.getType()));
        vo.setScaffold(ProjectBasic.Scaffold.getText(projectBasic.getScaffold()));
        vo.setGitHtmlUrl(projectBasic.getGitHtmlUrl());
        vo.setGitSshUrl(projectBasic.getGitSshUrl());
        vo.setGitHttpsUrl(projectBasic.getGitHttpsUrl());
        vo.setCreateTime(projectBasic.getGmtCreate());
        vo.setPushStatus(ProjectBasic.PushStatus.getText(projectBasic.getPushStatus()));
        vo.setReposStatus(ProjectBasic.ReposStatus.getText(projectBasic.getReposStatus()));
        return vo;
    }

    public static ProjectBasic convertToProjectBasic(CodeProjectCreateCmd cmd) {
        ProjectBasic entity = new ProjectBasic();
        entity.setName(cmd.getName());
        entity.setCode(cmd.getCode());
        entity.setDescription(cmd.getDescription());
        entity.setExtProperties(cmd.getExtProperties());
        entity.setReposStatus(ProjectBasic.ReposStatus.CREATING.getValue());
        return entity;
    }

    public static CodeProjectInfoRespDto convertToCodeProjectInfoVO(ProjectBasic projectBasic) {
        CodeProjectInfoRespDto vo = new CodeProjectInfoRespDto();
        vo.setId(projectBasic.getId());
        vo.setName(projectBasic.getName());
        vo.setCode(projectBasic.getCode());
        vo.setDescription(projectBasic.getDescription());
        vo.setType(ProjectBasic.Type.getText(projectBasic.getType()));
        vo.setScaffold(ProjectBasic.Scaffold.getText(projectBasic.getScaffold()));
        vo.setGitHtmlUrl(projectBasic.getGitHtmlUrl());
        vo.setGitHttpsUrl(projectBasic.getGitHttpsUrl());
        vo.setGitSshUrl(projectBasic.getGitSshUrl());
        vo.setPushStatus(ProjectBasic.PushStatus.getText(projectBasic.getPushStatus()));
        vo.setReposStatus(ProjectBasic.ReposStatus.getText(projectBasic.getReposStatus()));
        return vo;
    }
}
