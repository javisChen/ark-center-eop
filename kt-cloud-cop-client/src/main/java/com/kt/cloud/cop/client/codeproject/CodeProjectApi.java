package com.kt.cloud.cop.client.codeproject;


import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectCreateVo;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVo;
import com.kt.component.dto.PageResponse;
import com.kt.component.dto.PagingQuery;
import com.kt.component.dto.SingleResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CodeProjectApi {

    @PostMapping("/create")
    SingleResponse<CodeProjectCreateVo> create(@Validated @RequestBody CodeProjectCreateCmd cmd);

    @PostMapping("/pageList")
    SingleResponse<PageResponse<CodeProjectListVo>> pageList(@Validated @RequestBody PagingQuery pagingQuery);
}
