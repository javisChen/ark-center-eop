package com.kt.cloud.cop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectCreateVO1;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVO1;
import com.kt.component.dto.PagingQuery;

public interface ICodeProjectService {

    CodeProjectCreateVO1 createCodeProject(CodeProjectCreateCmd cmd);

    IPage<CodeProjectListVO1> pageListCodeProject(PagingQuery pagingDTO);
}
