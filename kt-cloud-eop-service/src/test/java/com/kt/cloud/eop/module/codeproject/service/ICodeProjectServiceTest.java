package com.kt.cloud.cop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.BaseTests;
import com.kt.cloud.cop.client.codeproject.query.CodeProjectListQuery;
import com.kt.cloud.cop.client.codeproject.vo.CodeProjectListVO;
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
        CodeProjectListQuery pagingDTO = new CodeProjectListQuery();
        pagingDTO.setCurrent(0);
        pagingDTO.setSize(10);
        IPage<CodeProjectListVO> codeProjectListVoIPage = iCodeProjectService.pageListCodeProject(pagingDTO);
        System.out.println(codeProjectListVoIPage);
        Assert.notNull(codeProjectListVoIPage, "");
    }
}