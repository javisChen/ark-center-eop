package com.kt.cloud.eop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.eop.module.codeproject.dto.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.module.codeproject.dto.query.request.CodeProjectListQueryReq;
import com.kt.cloud.eop.module.codeproject.dto.query.response.CodeProjectCreateRespDto;
import com.kt.cloud.eop.module.codeproject.dto.query.response.CodeProjectInfoRespDto;
import com.kt.cloud.eop.module.codeproject.dto.query.response.CodeProjectListRespDto;

public interface ICodeProjectService {

    CodeProjectCreateRespDto createCodeProject(CodeProjectCreateCmd cmd);

    IPage<CodeProjectListRespDto> pageListCodeProject(CodeProjectListQueryReq query);

    CodeProjectInfoRespDto getCodeProjectInfo(Long codeProjectId);
}
