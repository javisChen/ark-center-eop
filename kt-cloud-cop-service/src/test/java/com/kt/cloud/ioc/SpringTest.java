package com.kt.cloud.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("com.kt.cloud.ioc");
        Object bean = context.getBean("testBeanA");
        System.out.println(bean);
    }
}
