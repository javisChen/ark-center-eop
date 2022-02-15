package com.kt.cloud.eop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.eop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.client.codeproject.query.CodeProjectListQuery;
import com.kt.cloud.eop.client.codeproject.vo.CodeProjectCreateVO;
import com.kt.cloud.eop.client.codeproject.vo.CodeProjectInfoVO;
import com.kt.cloud.eop.client.codeproject.vo.CodeProjectListVO;

public interface ICodeProjectService {

    CodeProjectCreateVO createCodeProject(CodeProjectCreateCmd cmd);

    IPage<CodeProjectListVO> pageListCodeProject(CodeProjectListQuery query);

    CodeProjectInfoVO getCodeProjectInfo(Long codeProjectId);
}
