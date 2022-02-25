package com.kt.cloud.eop.module.codeproject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.eop.api.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.api.codeproject.query.request.CodeProjectListQueryReq;
import com.kt.cloud.eop.api.codeproject.query.response.CodeProjectCreateRespDto;
import com.kt.cloud.eop.api.codeproject.query.response.CodeProjectInfoRespDto;
import com.kt.cloud.eop.api.codeproject.query.response.CodeProjectListRespDto;
import com.kt.cloud.eop.module.codeproject.service.ICodeProjectService;
import com.kt.component.dto.PageResponse;
import com.kt.component.dto.SingleResponse;
import com.kt.component.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/code-project")
@RestController
public class CodeProjectController extends BaseController {

    @Autowired
    private ICodeProjectService iCodeProjectService;

    @PostMapping("/create")
    public SingleResponse<CodeProjectCreateRespDto> create(CodeProjectCreateCmd cmd) {
        return SingleResponse.ok(iCodeProjectService.createCodeProject(cmd));
    }

    @PostMapping("/pageList")
    public SingleResponse<PageResponse<CodeProjectListRespDto>> pageList(CodeProjectListQueryReq query) {
        IPage<CodeProjectListRespDto> vos = iCodeProjectService.pageListCodeProject(query);
        return SingleResponse.ok(PageResponse.build(vos));
    }

    @GetMapping("/info")
    public SingleResponse<CodeProjectInfoRespDto> info(Long codeProjectId) {
        CodeProjectInfoRespDto vo = iCodeProjectService.getCodeProjectInfo(codeProjectId);
        return SingleResponse.ok(vo);
    }

}
