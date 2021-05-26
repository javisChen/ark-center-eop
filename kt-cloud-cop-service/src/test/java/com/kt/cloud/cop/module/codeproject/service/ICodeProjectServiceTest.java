package com.kt.cloud.cop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.cop.BaseTests;
import com.kt.cloud.cop.module.codeproject.vo.CodeProjectListVo;
import com.kt.component.dto.PagingDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ICodeProjectServiceTest extends BaseTests {

    @Autowired
    private ICodeProjectService iCodeProjectService;

    @Test
    public void createCodeProject() {
    }

    @Test
    public void listVos() {
        PagingDTO pagingDTO = new PagingDTO();
        pagingDTO.setCurrent(0);
        pagingDTO.setSize(10);
        IPage<CodeProjectListVo> codeProjectListVoIPage = iCodeProjectService.listVos(pagingDTO);
        System.out.println(codeProjectListVoIPage);
        Assert.assertNotNull(codeProjectListVoIPage);
    }
}