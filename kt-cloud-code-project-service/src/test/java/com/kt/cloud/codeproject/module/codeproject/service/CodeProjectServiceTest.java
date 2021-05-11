package com.kt.cloud.codeproject.module.codeproject.service;

import com.kt.cloud.codeproject.module.codeproject.BaseTests;
import com.kt.cloud.codeproject.module.codeproject.GenCodeProjectDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CodeProjectServiceTest extends BaseTests {

    @Autowired
    private ICodeProjectService iCodeProjectService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void genCodeProject() {
        GenCodeProjectDTO genCodeProjectDTO = new GenCodeProjectDTO();
        genCodeProjectDTO.setName("kt-demo-123");
        genCodeProjectDTO.setDescription("demo123");
        genCodeProjectDTO.setArtifactId("kt-demo-123");
        genCodeProjectDTO.setGroupId("com.kt.cloud");
        genCodeProjectDTO.setPackageName("com.kt.cloud");
        iCodeProjectService.genCodeProject(genCodeProjectDTO);
    }
}