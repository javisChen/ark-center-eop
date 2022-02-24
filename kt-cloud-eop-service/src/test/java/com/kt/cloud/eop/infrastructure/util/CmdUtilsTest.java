package com.kt.cloud.eop.infrastructure.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CmdUtilsTest {

    @Test
    public void exec() throws IOException {
        File dir = new File("D:\\code\\javis\\kt-cloud-eop\\kt-cloud-eop-service\\c69ecf0fb2a446cca6941b1f73098663\\kt-cloud-order11");
        Map<String, String> environment = new HashMap<>(1);
        environment.put("REPOS_PATH", "git@github.com:javisChen/kt-cloud-order11.git");
        CmdUtils.exec(environment, dir, "cmd", "/c", "git_init.bat");
    }
}