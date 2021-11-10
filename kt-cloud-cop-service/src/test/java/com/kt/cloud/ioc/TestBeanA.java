package com.kt.cloud.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestBeanA {

    @Autowired
    private TestBeanB testBeanB;

    @Resource
    private TestBeanC testBeanC;
}
