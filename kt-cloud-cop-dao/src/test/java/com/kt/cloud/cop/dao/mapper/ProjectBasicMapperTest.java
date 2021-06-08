package com.kt.cloud.cop.dao.mapper;

import com.kt.cloud.cop.dao.entity.ProjectBasic;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class ProjectBasicMapperTest {

    @Autowired
    private ProjectBasicMapper projectBasicMapper;
    private SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public void setup() {
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(ProjectBasicMapperTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));

        projectBasicMapper = sqlSessionFactory.getConfiguration()
                .getMapper(ProjectBasicMapper.class,
                        sqlSessionFactory.openSession(true));

    }

    @Test
    public void test() {
        List<ProjectBasic> projectBasics = projectBasicMapper.selectList(null);
        System.out.println(projectBasics);
        Assert.notEmpty(projectBasics, "List为空");
    }

}