package com.kt.cloud.cop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.module.codeproject.GenCodeProjectDTO;
import com.kt.cloud.cop.module.codeproject.vo.CodeProjectGenVo;
import com.kt.cloud.cop.module.codeproject.vo.CodeProjectListVo;
import com.kt.component.dto.PagingDTO;

public interface ICodeProjectService {

    CodeProjectGenVo createCodeProject(GenCodeProjectDTO genCodeProjectDTO);

    IPage<CodeProjectListVo> listVos(PagingDTO pagingDTO);


}
