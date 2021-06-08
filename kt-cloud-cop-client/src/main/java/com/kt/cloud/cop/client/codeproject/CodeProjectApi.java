package com.kt.cloud.cop.client.codeproject;


import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectCreateVO;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectInfoVO;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVO;
import com.kt.component.dto.PageResponse;
import com.kt.component.dto.PagingQuery;
import com.kt.component.dto.SingleResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/code-project")
public interface CodeProjectApi {

    @PostMapping("/create")
    SingleResponse<CodeProjectCreateVO> create(@Validated @RequestBody CodeProjectCreateCmd cmd);

    @PostMapping("/pageList")
    SingleResponse<PageResponse<CodeProjectListVO>> pageList(@Validated @RequestBody PagingQuery pagingQuery);

    @GetMapping("/info")
    SingleResponse<CodeProjectInfoVO> info(Long codeProjectId);
}
