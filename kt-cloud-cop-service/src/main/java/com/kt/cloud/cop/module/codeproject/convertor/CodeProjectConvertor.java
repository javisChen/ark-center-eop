package com.kt.cloud.cop.module.codeproject.convertor;

import com.kt.cloud.cop.dao.entity.ProjectBasic;
import com.kt.cloud.cop.module.codeproject.vo.CodeProjectListVo;
import com.kt.cloud.cop.module.git.GitCreate;
import com.kt.cloud.cop.module.codeproject.GenCodeProjectDTO;

public class CodeProjectConvertor {

    public static GitCreate convertForCreate(GenCodeProjectDTO genCodeProjectDTO) {
        GitCreate gitCreate = new GitCreate();
        gitCreate.setName(genCodeProjectDTO.getName());
        gitCreate.setDescription(genCodeProjectDTO.getDescription());
        return gitCreate;
    }

    public static CodeProjectListVo convertToCodeProjectListVo(ProjectBasic projectBasic) {
        CodeProjectListVo codeProjectListVo = new CodeProjectListVo();
        codeProjectListVo.setName(projectBasic.getName());
        codeProjectListVo.setCode(projectBasic.getCode());
        codeProjectListVo.setDescription(projectBasic.getDescription());
        codeProjectListVo.setType(projectBasic.getType());
        codeProjectListVo.setGitReposUrl(projectBasic.getGitReposUrl());
        return codeProjectListVo;
    }
}
