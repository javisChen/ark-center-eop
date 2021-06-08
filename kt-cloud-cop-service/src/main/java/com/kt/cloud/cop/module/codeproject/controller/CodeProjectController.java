package com.kt.cloud.cop.module.codeproject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.client.codeproject.CodeProjectApi;
import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectCreateVO1;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVO1;
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
    public SingleResponse<CodeProjectCreateVO1> create(CodeProjectCreateCmd cmd) {
        return SingleResponse.ok(iCodeProjectService.createCodeProject(cmd));
    }

    @Override
    public SingleResponse<PageResponse<CodeProjectListVO1>> pageList(PagingQuery pagingQuery) {
        IPage<CodeProjectListVO1> vos = iCodeProjectService.pageListCodeProject(pagingQuery);
        return SingleResponse.ok(PageResponse.build(vos));
    }
}
