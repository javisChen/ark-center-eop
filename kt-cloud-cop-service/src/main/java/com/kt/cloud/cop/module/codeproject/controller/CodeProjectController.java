package com.kt.cloud.cop.module.codeproject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.module.codeproject.GenCodeProjectDTO;
import com.kt.cloud.cop.module.codeproject.service.ICodeProjectService;
import com.kt.cloud.cop.module.codeproject.vo.CodeProjectGenVo;
import com.kt.cloud.cop.module.codeproject.vo.CodeProjectListVo;
import com.kt.component.dto.PageResponse;
import com.kt.component.dto.PagingDTO;
import com.kt.component.dto.SingleResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class CodeProjectController {

    @Autowired
    private ICodeProjectService iCodeProjectService;

    @PostMapping("/code-project/create")
    public SingleResponse<CodeProjectGenVo> create(@Validated @RequestBody GenCodeProjectDTO dto) {
        return SingleResponse.ok(iCodeProjectService.createCodeProject(dto));
    }

    @PostMapping("/code-project/pageList")
    public SingleResponse<PageResponse<CodeProjectListVo>> list(@Validated @RequestBody PagingDTO pagingDTO) {
        IPage<CodeProjectListVo> vos = iCodeProjectService.listVos(pagingDTO);
        return SingleResponse.ok(PageResponse.build(vos));
    }
}
