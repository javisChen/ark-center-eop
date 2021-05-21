package com.kt.cloud.cop.module.codeproject.controller;

import com.kt.cloud.cop.module.codeproject.GenCodeProjectDTO;
import com.kt.cloud.cop.module.codeproject.service.ICodeProjectService;
import com.kt.cloud.cop.module.codeproject.vo.CodeProjectGenVo;
import com.kt.component.dto.SingleResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code-project")
public class CodeProjectController {

    @Autowired
    private ICodeProjectService iCodeProjectService;

    @PostMapping("/gen")
    public SingleResponse<CodeProjectGenVo> create(@Validated @RequestBody GenCodeProjectDTO dto) {
        return SingleResponse.ok(iCodeProjectService.createCodeProject(dto));
    }
}
