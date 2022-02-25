package com.kt.cloud.eop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.eop.api.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.api.codeproject.query.request.CodeProjectListQueryReq;
import com.kt.cloud.eop.api.codeproject.query.response.CodeProjectCreateRespDto;
import com.kt.cloud.eop.api.codeproject.query.response.CodeProjectInfoRespDto;
import com.kt.cloud.eop.api.codeproject.query.response.CodeProjectListRespDto;

public interface ICodeProjectService {

    CodeProjectCreateRespDto createCodeProject(CodeProjectCreateCmd cmd);

    IPage<CodeProjectListRespDto> pageListCodeProject(CodeProjectListQueryReq query);

    CodeProjectInfoRespDto getCodeProjectInfo(Long codeProjectId);
}
