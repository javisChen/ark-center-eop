package com.kt.cloud.codeproject.manager.git.gitee;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.kt.cloud.codeproject.manager.git.GitManager;
import com.kt.cloud.codeproject.manager.git.gitee.request.GiteeCreateReposRequest;
import com.kt.cloud.codeproject.manager.git.gitee.response.GiteeCreateReposResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GiteeManager implements GitManager {

    private final String TAG = "[请求GiteeOpenAPI] -> ";

    @Override
    public GiteeCreateReposResponse createRepos(GiteeCreateReposRequest req) {
        HttpRequest httpRequest = HttpUtil.createPost(GiteeConstants.CREATE_REPOS_URI);
        httpRequest.form("access_token", req.getAccessToken());
        httpRequest.form("name", req.getName());
        httpRequest.form("description", req.getDescription());
        logDebug(String.format(TAG + "[创建仓库]-[请求参数]-[%s]", httpRequest.form()));
        HttpResponse response = httpRequest.execute();
        if (response.getStatus() != HttpStatus.HTTP_OK) {
            log.error(TAG + "[响应错误]");
            log.error(String.format(TAG + "[状态码]-[%s]", response.getStatus()));
            log.error(String.format(TAG + "[Body]-[%s]", response.body()));
            throw new GitApiException(response.getStatus(), response.body());
        }
        logDebug(TAG + "成功");
        logDebug(TAG + "响应：" + response.body());
        return JSONObject.parseObject(response.body(), GiteeCreateReposResponse.class);
    }

    private void logDebug(String s) {
        if (log.isDebugEnabled()) {
            log.debug(s);
        }
    }
}
