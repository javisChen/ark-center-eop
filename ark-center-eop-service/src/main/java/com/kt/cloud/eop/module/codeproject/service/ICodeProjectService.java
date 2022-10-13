package com.ark.center.eop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ark.center.eop.module.codeproject.dto.cmd.CodeProjectCreateCmd;
import com.ark.center.eop.module.codeproject.dto.query.request.CodeProjectListQueryReq;
import com.ark.center.eop.module.codeproject.dto.query.response.CodeProjectCreateRespDto;
import com.ark.center.eop.module.codeproject.dto.query.response.CodeProjectInfoRespDto;
import com.ark.center.eop.module.codeproject.dto.query.response.CodeProjectListRespDto;

public interface ICodeProjectService {

    CodeProjectCreateRespDto createCodeProject(CodeProjectCreateCmd cmd);

    IPage<CodeProjectListRespDto> pageListCodeProject(CodeProjectListQueryReq query);

    CodeProjectInfoRespDto getCodeProjectInfo(Long codeProjectId);
}
