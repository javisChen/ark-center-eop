package com.kt.cloud.eop.infrastructure.generate;

import com.kt.cloud.eop.module.codeproject.generate.project.SpringCloudProjectGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SpringCloudProjectGeneratorTest {

    private SpringCloudProjectGenerator projectGenerator = new SpringCloudProjectGenerator();

    @Test
    public void generator() {
        Map<String, Object> map = new HashMap<>();
        map.put("artifactId", "order");
        map.put("groupId", "com.kt.cloud");
        map.put("packageName", "com.kt.cloud");
        projectGenerator.generator(map);
    }

    @Test
    public void test() {
        File daoModule = projectGenerator.findDaoModule(new File("/Users/chenjiawei/code/myself/kt-cloud"));
        System.out.println(daoModule);
    }
}