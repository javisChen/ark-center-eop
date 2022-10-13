package com.ark.center.eop.module.git.service;

import cn.hutool.system.SystemUtil;
import cn.hutool.system.oshi.OshiUtil;
import com.ark.center.eop.infrastructure.util.CmdUtils;
import com.ark.center.eop.manager.git.GitCreateReposRequest;
import com.ark.center.eop.manager.git.GitCreateReposResponse;
import com.ark.center.eop.manager.git.GitManager;
import com.ark.center.eop.manager.git.gitee.GitApiException;
import com.ark.center.eop.module.git.GitCreate;
import com.ark.component.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class GitService {

    private final GitManager gitManager;

    public GitService(GitManager gitManager) {
        this.gitManager = gitManager;
    }

    public GitCreateReposResponse createRepository(GitCreate gitCreate) {
        GitCreateReposRequest gitCreateReposRequest = createGitCreateReposRequest(gitCreate);
        GitCreateReposResponse repos;
        try {
            repos = gitManager.createRepos(gitCreateReposRequest);
        } catch (GitApiException e) {
            log.error("[Git仓库创建失败]：", e);
            throw new BizException(e.getMessage());
        }
        return repos;
    }

    private GitCreateReposRequest createGitCreateReposRequest(GitCreate gitCreate) {
        GitCreateReposRequest gitCreateReposRequest = new GitCreateReposRequest();
        gitCreateReposRequest.setName(gitCreate.getName());
        gitCreateReposRequest.setDescription(gitCreate.getDescription());
        return gitCreateReposRequest;
    }

    public boolean intiAndPushToRepos(File file, String code, String gitReposUrl) {
        File dir = new File(file + File.separator + code);
        Map<String, String> environment = new HashMap<>(1);
        environment.put("REPOS_PATH", gitReposUrl);
        try {
            boolean windows = SystemUtil.getOsInfo().isWindows();
            if (windows) {
                CmdUtils.exec(environment, dir, "cmd", "/c", "git_init.bat");
            } else {
                CmdUtils.exec(environment, dir, "chmod", "+x", "git_init.sh");
                CmdUtils.exec(environment, dir, "./git_init.sh");
            }
        } catch (IOException e) {
            log.error("[初始化仓库][失败]", e);
            return false;
        }
        return true;
    }
}
