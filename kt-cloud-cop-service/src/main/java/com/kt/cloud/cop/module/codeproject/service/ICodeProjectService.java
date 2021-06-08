package com.kt.cloud.cop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectCreateVO;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectInfoVO;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVO;
import com.kt.component.dto.PagingQuery;

public interface ICodeProjectService {

    CodeProjectCreateVO createCodeProject(CodeProjectCreateCmd cmd);

    IPage<CodeProjectListVO> pageListCodeProject(PagingQuery pagingDTO);

    CodeProjectInfoVO getCodeProjectInfo(Long codeProjectId);
}
