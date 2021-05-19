package com.kt.cloud.cop.dao.mapper;

import com.kt.cloud.cop.BaseTests;
import com.kt.cloud.cop.dao.entity.ProjectBasic;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectBasicMapperTest extends BaseTests {

    @Autowired
    private ProjectBasicMapper projectBasicMapper;

    @Test
    public void test() {
        List<ProjectBasic> projectBasics = projectBasicMapper.selectList(null);
        System.out.println(projectBasics);
        Assert.assertNotNull(projectBasics);
    }

}