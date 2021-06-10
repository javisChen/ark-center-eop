package com.kt.cloud.cop.module.codeproject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.client.codeproject.CodeProjectApi;
import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectCreateVO;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectInfoVO;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVO;
import com.kt.cloud.cop.module.codeproject.service.ICodeProjectService;
import com.kt.component.dto.PageResponse;
import com.kt.component.dto.PagingQuery;
import com.kt.component.dto.SingleResponse;
import com.kt.component.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeProjectController extends BaseController implements CodeProjectApi {

    @Autowired
    private ICodeProjectService iCodeProjectService;

    @Override
    public SingleResponse<CodeProjectCreateVO> create(CodeProjectCreateCmd cmd) {
        return SingleResponse.ok(iCodeProjectService.createCodeProject(cmd));
    }

    @Override
    public SingleResponse<PageResponse<CodeProjectListVO>> pageList(PagingQuery pagingQuery) {
        IPage<CodeProjectListVO> vos = iCodeProjectService.pageListCodeProject(pagingQuery);
        return SingleResponse.ok(PageResponse.build(vos));
    }

    @Override
    public SingleResponse<CodeProjectInfoVO> info(Long codeProjectId) {
        CodeProjectInfoVO vo = iCodeProjectService.getCodeProjectInfo(codeProjectId);
        return SingleResponse.ok(vo);
    }
}