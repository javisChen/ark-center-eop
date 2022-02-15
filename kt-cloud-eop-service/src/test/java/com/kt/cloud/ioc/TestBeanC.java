package com.kt.cloud.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestBeanC {

    @Autowired
    private TestBeanA testBeanA;
}
