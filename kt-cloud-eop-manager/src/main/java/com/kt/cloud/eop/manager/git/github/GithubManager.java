package com.kt.cloud.eop.manager.git.github;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.kt.cloud.eop.manager.git.GitCreateReposRequest;
import com.kt.cloud.eop.manager.git.GitCreateReposResponse;
import com.kt.cloud.eop.manager.git.GitManager;
import com.kt.cloud.eop.manager.git.config.GithubConfiguration;
import com.kt.cloud.eop.manager.git.gitee.GitApiException;
import com.kt.cloud.eop.manager.git.gitee.request.GiteeGetTokenRequest;
import com.kt.cloud.eop.manager.git.gitee.response.GiteeGetTokenResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GithubManager implements GitManager {

    private final GithubConfiguration githubConfiguration;

    public GithubManager(GithubConfiguration githubConfiguraion) {
        this.githubConfiguration = githubConfiguraion;
    }

    @Override
    public GitCreateReposResponse createRepos(GitCreateReposRequest req) {
        HttpRequest httpRequest = HttpUtil.createPost(GithubConstants.CREATE_REPOS_URI);
        httpRequest.header("Authorization", githubConfiguration.getAuthorization());
        String body = JSONObject.toJSONString(req);
        httpRequest.body(body);
        log.debug(String.format("[创建仓库]-[请求参数]-[%s]", body));
        HttpResponse response = getResponse(httpRequest);
        return JSONObject.parseObject(response.body(), GitCreateReposResponse.class);
    }

    @Override
    public GiteeGetTokenResponse getToken(GiteeGetTokenRequest req) {
        return null;
    }


    private HttpResponse getResponse(HttpRequest httpRequest) {
        HttpResponse response = httpRequest.execute();
        if (response.getStatus() != HttpStatus.HTTP_CREATED && response.getStatus() != HttpStatus.HTTP_OK) {
            log.error("[响应错误]");
            log.error(String.format("[状态码]-[%s]", response.getStatus()));
            log.error(String.format("[Body]-[%s]", response.body()));
            throw new GitApiException(response.getStatus(), String.format("Git创建仓库失败：[%s]", response.body()));
        }
        log.debug("成功");
        log.debug("响应：" + response.body());
        return response;
    }
}
