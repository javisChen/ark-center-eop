package com.ark.center.eop.manager.git.gitee;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.ark.center.eop.manager.git.GitCreateReposRequest;
import com.ark.center.eop.manager.git.GitCreateReposResponse;
import com.ark.center.eop.manager.git.GitManager;
import com.ark.center.eop.manager.git.config.GiteeConfiguration;
import com.ark.center.eop.manager.git.gitee.request.GiteeGetTokenRequest;
import com.ark.center.eop.manager.git.gitee.response.GiteeGetTokenResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GiteeManager implements GitManager {

    private final String TAG = "[请求GiteeOpenAPI] -> ";

    private final GiteeConfiguration giteeConfiguration;

    public GiteeManager(GiteeConfiguration giteeConfiguration) {
        this.giteeConfiguration = giteeConfiguration;
    }

    @Override
    public GitCreateReposResponse createRepos(GitCreateReposRequest req) {
        HttpRequest httpRequest = HttpUtil.createPost(GiteeConstants.CREATE_REPOS_URI);
        httpRequest.form("access_token", giteeConfiguration.getAccessToken());
        httpRequest.form("name", req.getName());
        httpRequest.form("description", req.getDescription());
        log.debug(String.format(TAG + "[创建仓库]-[请求参数]-[%s]", httpRequest.form()));
        HttpResponse response = getResponse(httpRequest);
        return JSONObject.parseObject(response.body(), GitCreateReposResponse.class);
    }

    @Override
    public GiteeGetTokenResponse getToken(GiteeGetTokenRequest req) {
        HttpRequest httpRequest = HttpUtil.createPost(GiteeConstants.GET_TOKEN_URI);
        httpRequest.form("grant_type", "password");
        httpRequest.form("username", req.getUsername());
        httpRequest.form("password", req.getPassword());
        httpRequest.form("client_id", req.getClientId());
        httpRequest.form("client_secret", req.getClientSecret());
        httpRequest.form("scope", req.getScope());
        log.debug(String.format(TAG + "[获取Token]-[请求参数]-[%s]", httpRequest.form()));
        HttpResponse response = getResponse(httpRequest);
        return GiteeGetTokenResponse.fromString(response.body());
    }

    private HttpResponse getResponse(HttpRequest httpRequest) {
        HttpResponse response = httpRequest.execute();
        if (response.getStatus() != HttpStatus.HTTP_CREATED && response.getStatus() != HttpStatus.HTTP_OK) {
            log.error(TAG + "[响应错误]");
            log.error(String.format(TAG + "[状态码]-[%s]", response.getStatus()));
            log.error(String.format(TAG + "[Body]-[%s]", response.body()));
            throw new GitApiException(response.getStatus(), String.format("Git创建仓库失败：[%s]", response.body()));
        }
        log.debug(TAG + "成功");
        log.debug(TAG + "响应：" + response.body());
        return response;
    }

}
