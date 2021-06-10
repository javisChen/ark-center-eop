package com.kt.cloud.cop.infrastructure.generate;

import com.kt.cloud.cop.module.codeproject.generate.project.SpringCloudProjectGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SpringCloudProjectGeneratorTest {

    private SpringCloudProjectGenerator projectGenerator = new SpringCloudProjectGenerator();

    @Test
    public void generator() {
        Map<String, Object> map = new HashMap<>();
        map.put("artifactId", "demo");
        map.put("groupId", "com.kt.cloud");
        map.put("packageName", "com.kt.cloud");
        projectGenerator.generator(map);
    }

    @Test
    public void test() {
        File daoModule = projectGenerator.findDaoModule(new File("/Users/chenjiawei/code/myself/kt-cloud-cop/357315e697824a0cac06439b1a51990a"));
        System.out.println(daoModule);
    }
}