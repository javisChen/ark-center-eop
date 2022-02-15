package com.kt.cloud.cop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.query.CodeProjectListQuery;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectCreateVO;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectInfoVO;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVO;

public interface ICodeProjectService {

    CodeProjectCreateVO createCodeProject(CodeProjectCreateCmd cmd);

    IPage<CodeProjectListVO> pageListCodeProject(CodeProjectListQuery query);

    CodeProjectInfoVO getCodeProjectInfo(Long codeProjectId);
}
