package com.kt.cloud.cop.module.codeproject.convertor;

import com.kt.cloud.cop.module.git.GitCreate;
import com.kt.cloud.cop.module.codeproject.GenCodeProjectDTO;

public class CodeProjectConvertor {

    public static GitCreate convertForCreate(GenCodeProjectDTO genCodeProjectDTO) {
        GitCreate gitCreate = new GitCreate();
        gitCreate.setName(genCodeProjectDTO.getName());
        gitCreate.setDescription(genCodeProjectDTO.getDescription());
        return gitCreate;
    }
}
