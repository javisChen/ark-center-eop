package com.kt.cloud.eop.module.codeproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kt.cloud.eop.BaseTests;
import com.kt.cloud.eop.module.codeproject.dto.query.request.CodeProjectListQueryReq;
import com.kt.cloud.eop.module.codeproject.dto.query.response.CodeProjectListRespDto;
import com.kt.component.cache.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


public class ICodeProjectServiceTest extends BaseTests {

    @Autowired
    private ICodeProjectService iCodeProjectService;
    @Autowired
    private RedisService redisService;

    @Test
    public void createCodeProject() {
        redisService.set("key", "test");
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