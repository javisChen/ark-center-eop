package com.ark.center.eop.module.codeproject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ark.center.eop.module.codeproject.dto.cmd.CodeProjectCreateCmd;
import com.ark.center.eop.module.codeproject.dto.query.request.CodeProjectListQueryReq;
import com.ark.center.eop.module.codeproject.dto.query.response.CodeProjectCreateRespDto;
import com.ark.center.eop.module.codeproject.dto.query.response.CodeProjectInfoRespDto;
import com.ark.center.eop.module.codeproject.dto.query.response.CodeProjectListRespDto;
import com.ark.center.eop.module.codeproject.service.ICodeProjectService;
import com.ark.component.dto.PageResponse;
import com.ark.component.dto.SingleResponse;
import com.ark.component.web.base.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/code-project")
@RestController
public class CodeProjectController extends BaseController {
    private final ICodeProjectService iCodeProjectService;

    public CodeProjectController(ICodeProjectService iCodeProjectService) {
        this.iCodeProjectService = iCodeProjectService;
    }

    @PostMapping("/create")
    public SingleResponse<CodeProjectCreateRespDto> create(@RequestBody @Validated CodeProjectCreateCmd cmd) {
        return SingleResponse.ok(iCodeProjectService.createCodeProject(cmd));
    }

    @PostMapping("/pageList")
    public SingleResponse<PageResponse<CodeProjectListRespDto>> pageList(CodeProjectListQueryReq query) {
        IPage<CodeProjectListRespDto> vos = iCodeProjectService.pageListCodeProject(query);
        return SingleResponse.ok(PageResponse.of(vos));
    }

    @GetMapping("/info")
    public SingleResponse<CodeProjectInfoRespDto> info(Long codeProjectId) {
        CodeProjectInfoRespDto vo = iCodeProjectService.getCodeProjectInfo(codeProjectId);
        return SingleResponse.ok(vo);
    }

}
