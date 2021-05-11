package com.kt.cloud.codeproject.generate;

import com.kt.cloud.codeproject.generate.model.JavaProjectGenerateParam;
import org.junit.Test;

public class JavaProjectGeneratorTest {

    private ProjectGenerator<JavaProjectGenerateParam> projectGenerator = new JavaProjectGenerator();

    @Test
    public void generator() {
        JavaProjectGenerateParam javaProjectGenerateParam = new JavaProjectGenerateParam();
        javaProjectGenerateParam.setArtifactId("demo");
        javaProjectGenerateParam.setGroupId("com.kt.cloud.demo");
        javaProjectGenerateParam.setPackageName("com.kt.cloud.demo");
        projectGenerator.generator(javaProjectGenerateParam);
    }
}