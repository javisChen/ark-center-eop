package com.kt.cloud.cop.module.codeproject.service;

import com.kt.cloud.cop.module.codeproject.GenCodeProjectDTO;
import com.kt.cloud.cop.module.codeproject.vo.CodeProjectGenVo;

public interface ICodeProjectService {

    CodeProjectGenVo createCodeProject(GenCodeProjectDTO genCodeProjectDTO);

}
