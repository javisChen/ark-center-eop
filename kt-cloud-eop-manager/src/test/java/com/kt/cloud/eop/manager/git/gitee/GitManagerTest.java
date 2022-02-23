package com.kt.cloud.eop.manager.git.gitee;


import cn.hutool.core.util.RuntimeUtil;
import com.kt.cloud.eop.manager.git.config.GiteeConfiguration;
import com.kt.cloud.eop.manager.git.gitee.request.GiteeCreateReposRequest;
import com.kt.cloud.eop.manager.git.gitee.request.GiteeGetTokenRequest;
import com.kt.cloud.eop.manager.git.gitee.response.GiteeGetTokenResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class GitManagerTest {

    private GiteeManager giteeManager;
    private String username = "javischen9548@foxmail.com";
    private String password = "javis9548chen";
    private String clientId = "dc7f80b02163e4f76fbcffb03eff926566aaf19f2c51a0339391a599d60b32ba";
    private String clientSecret = "b68bc45b2f30ee460e8dfa468242697f276b144778bfb9011e29ee4253e8d03d";
    private String accessToken = "3814b82ee62c6d47511fd238879e2561";

    @BeforeAll
    public void setUp() throws Exception {
        GiteeConfiguration giteeConfiguration = new GiteeConfiguration();
        giteeConfiguration.setAccessToken(accessToken);
        giteeManager = new GiteeManager(giteeConfiguration);
    }

    @Test
    public void createRepos() {
        GiteeCreateReposRequest request = new GiteeCreateReposRequest();
        request.setName("cloudTest7");
        request.setDescription("cloudTest7");
        giteeManager.createRepos(request);
    }

    @Test
    public void getToken() {
        GiteeGetTokenRequest request = new GiteeGetTokenRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);
        request.setScope("user_info projects pull_requests issues notes keys hook groups gists enterprises");
        GiteeGetTokenResponse token = giteeManager.getToken(request);
        System.out.println(token);
    }

    @Test
    public void test() throws IOException, InterruptedException {
        String path = "/Users/chenjiawei/code/myself/kt-cloud-eop/c2b9a64c108b49c6b37b0f259b703433/kt-demo-1240";
        String[] cmd = {"chmod", "+x", "git_init.sh"};
//        String[] cmd = {"ls"};
        String s = exec(path, cmd);
        cmd = new String[]{"./git_init.sh"};
        s = exec(path, cmd);
        System.out.println(s);

    }

    private String exec(String path, String[] cmd) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(new File(path));
        processBuilder.environment().put("GIT_PATH", "321");
        processBuilder.command(cmd);
        InputStream inputStream = processBuilder.start().getInputStream();
        Process ll = RuntimeUtil.exec(new String[]{"GIT_PATH=321"}, new File(path), cmd);
        String s = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        return s;
    }
}