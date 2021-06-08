package com.kt.cloud.cop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.BaseTests;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVo;
import com.kt.component.dto.PagingQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


public class ICodeProjectServiceTest extends BaseTests {

    @Autowired
    private ICodeProjectService iCodeProjectService;

    @Test
    public void createCodeProject() {
    }

    @Test
    public void listVos() {
        PagingQuery pagingDTO = new PagingQuery();
        pagingDTO.setCurrent(0);
        pagingDTO.setSize(10);
        IPage<CodeProjectListVo> codeProjectListVoIPage = iCodeProjectService.pageListCodeProject(pagingDTO);
        System.out.println(codeProjectListVoIPage);
        Assert.notNull(codeProjectListVoIPage, "");
    }
}