package com.kt.cloud.eop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.eop.api.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.api.codeproject.query.CodeProjectListQuery;
import com.kt.cloud.eop.api.codeproject.vo.CodeProjectCreateVO;
import com.kt.cloud.eop.api.codeproject.vo.CodeProjectInfoVO;
import com.kt.cloud.eop.api.codeproject.vo.CodeProjectListVO;

public interface ICodeProjectService {

    CodeProjectCreateVO createCodeProject(CodeProjectCreateCmd cmd);

    IPage<CodeProjectListVO> pageListCodeProject(CodeProjectListQuery query);

    CodeProjectInfoVO getCodeProjectInfo(Long codeProjectId);
}
