package com.ark.center.eop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ark.center.eop.BaseTests;
import com.ark.center.eop.module.codeproject.dto.query.request.CodeProjectListQueryReq;
import com.ark.center.eop.module.codeproject.dto.query.response.CodeProjectListRespDto;
import com.ark.component.cache.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


public class ICodeProjectServiceTest extends BaseTests {

    @Autowired
    private ICodeProjectService iCodeProjectService;
    @Autowired
    private CacheService cacheService;

    @Test
    public void createCodeProject() {
        cacheService.set("key", "test");
    }

    @Test
    public void listVos() {
        CodeProjectListQueryReq pagingDTO = new CodeProjectListQueryReq();
        pagingDTO.setCurrent(0);
        pagingDTO.setSize(10);
        IPage<CodeProjectListRespDto> codeProjectListVoIPage = iCodeProjectService.pageListCodeProject(pagingDTO);
        System.out.println(codeProjectListVoIPage);
        Assert.notNull(codeProjectListVoIPage, "");
    }
}