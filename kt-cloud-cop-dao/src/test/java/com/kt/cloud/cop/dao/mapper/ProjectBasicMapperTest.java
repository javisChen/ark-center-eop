package com.kt.cloud.cop.dao.mapper;

import com.kt.cloud.cop.BaseTests;
import com.kt.cloud.cop.dao.entity.ProjectBasic;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectBasicMapperTest {

    @Autowired
    private ProjectBasicMapper projectBasicMapper;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setup() {
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(ProjectBasicMapperTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));

        projectBasicMapper = sqlSessionFactory.getConfiguration()
                .getMapper(ProjectBasicMapper.class,
                        sqlSessionFactory.openSession(true));

    }

    @Test
    public void test() {
        List<ProjectBasic> projectBasics = projectBasicMapper.selectList();
        System.out.println(projectBasics);
        Assert.assertNotNull(projectBasics);
    }

}