package com.kt.cloud.cop.module.git;

import com.kt.cloud.cop.infrastructure.config.GitProperties;
import com.kt.cloud.cop.infrastructure.constant.RedisKeyConst;
import com.kt.cloud.cop.infrastructure.util.CmdUtils;
import com.kt.cloud.cop.manager.git.GitManager;
import com.kt.cloud.cop.manager.git.gitee.request.GiteeCreateReposRequest;
import com.kt.cloud.cop.manager.git.gitee.request.GiteeGetTokenRequest;
import com.kt.cloud.cop.manager.git.gitee.response.GiteeCreateReposResponse;
import com.kt.cloud.cop.manager.git.gitee.response.GiteeGetTokenResponse;
import com.kt.component.redis.RedisService;
import com.kt.toolkit.log.Logs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class GitService {

    @Autowired
    private GitManager gitManager;

    @Autowired
    private RedisService redisService;

    @Autowired
    private GitProperties gitProperties;

    public GitReposInfo createRepository(GitCreate gitCreate) {
        GiteeCreateReposRequest giteeCreateReposRequest = new GiteeCreateReposRequest();
        giteeCreateReposRequest.setName(gitCreate.getName());
        giteeCreateReposRequest.setDescription(gitCreate.getDescription());
        giteeCreateReposRequest.setAccessToken(getAccessToken(gitProperties));
        GiteeCreateReposResponse repos = gitManager.createRepos(giteeCreateReposRequest);
        return new GitReposInfo(repos.getHtmlUrl(), gitCreate.getName());
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
        GiteeGetTokenResponse getTokenResponse = gitManager.getToken(req);
        accessToken = getTokenResponse.getAccessToken();
        redisService.set(getAccessTokenRedisKey(gitProperties.getClientId()), accessToken, (long) (getTokenResponse.getExpiresIn() / 60));
        return accessToken;
    }

    private String getAccessTokenRedisKey(String clientId) {
        return RedisKeyConst.GIT_ACCESS_TOKEN_KEY_PREFIX + clientId;
    }

    public boolean intiAndPushToRepos(File file, GitReposInfo gitReposInfo) {
        File dir = new File(file + File.separator + gitReposInfo.getReposName());
        Map<String, String> environment = new HashMap<>();
        environment.put("REPOS_PATH", gitReposInfo.getReposUrl());
        try {
            CmdUtils.exec(environment, dir, "chmod", "+x", "git_init.sh");
            CmdUtils.exec(environment, dir, "./git_init.sh");
        } catch (IOException e) {
            log.error("[初始化仓库][失败]", e);
            return false;
        }
        return true;
    }
}
