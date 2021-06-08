package com.kt.cloud.cop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectCreateVo;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVo;
import com.kt.component.dto.PagingQuery;

public interface ICodeProjectService {

    CodeProjectCreateVo createCodeProject(CodeProjectCreateCmd cmd);

    IPage<CodeProjectListVo> pageListCodeProject(PagingQuery pagingDTO);
}
