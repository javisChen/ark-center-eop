package com.kt.cloud.codeproject.git;

import cn.hutool.core.util.RuntimeUtil;
import com.kt.cloud.codeproject.config.GitProperties;
import com.kt.cloud.codeproject.infrastructure.constant.RedisKeyConst;
import com.kt.cloud.codeproject.manager.git.GitManager;
import com.kt.cloud.codeproject.manager.git.gitee.request.GiteeCreateReposRequest;
import com.kt.cloud.codeproject.manager.git.gitee.request.GiteeGetTokenRequest;
import com.kt.cloud.codeproject.manager.git.gitee.response.GiteeCreateReposResponse;
import com.kt.cloud.codeproject.manager.git.gitee.response.GiteeGetTokenResponse;
import com.kt.component.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class GitService {

    @Autowired
    private GitManager gitManager;

    @Autowired
    private RedisService redisService;

    @Autowired
    private GitProperties gitProperties;

    public String createRepository(GitCreate gitCreate) {
        GiteeCreateReposRequest giteeCreateReposRequest = new GiteeCreateReposRequest();
        giteeCreateReposRequest.setName(gitCreate.getName());
        giteeCreateReposRequest.setDescription(gitCreate.getDescription());
        giteeCreateReposRequest.setAccessToken(getAccessToken(gitProperties));
        GiteeCreateReposResponse repos = gitManager.createRepos(giteeCreateReposRequest);
        return repos.getHtmlUrl();
    }

    private String getAccessToken(GitProperties gitProperties) {
        String accessToken = ((String) redisService.get(getAccessTokenRedisKey(gitProperties.getClientId())));
        if (StringUtils.isNotEmpty(accessToken)) {
            return accessToken;
        }
        GiteeGetTokenRequest req = new GiteeGetTokenRequest();
        req.setUsername(gitProperties.getEmail());
        req.setPassword(gitProperties.getPassword());
        req.setClientId(gitProperties.getClientId());
        req.setClientSecret(gitProperties.getClientSecret());
        req.setScope(gitProperties.getScope());
        GiteeGetTokenResponse token = gitManager.getToken(req);
        redisService.set(getAccessTokenRedisKey(gitProperties.getClientId()), token, (long) (token.getExpiresIn() / 60));
        return token.getAccessToken();
    }

    private String getAccessTokenRedisKey(String clientId) {
        return RedisKeyConst.GIT_ACCESS_TOKEN_KEY_PREFIX + clientId;
    }

    public boolean intiAndPushToRepos(File file, String repoUrl) {
        String[] cmd = {"./git_init.sh"};
        String[] envp = {"REPOS_PATH=" + repoUrl};
        Process ll = RuntimeUtil.exec(envp, file, cmd);
        InputStream inputStream = ll.getInputStream();
        try {
            String s = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
