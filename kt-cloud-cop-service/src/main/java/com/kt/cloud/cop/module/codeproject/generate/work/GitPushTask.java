package com.kt.cloud.cop.module.codeproject.generate.work;

import cn.hutool.core.io.FileUtil;
import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.client.codeproject.enums.ReposSourceEnums;
import com.kt.cloud.cop.module.codeproject.convertor.CodeProjectConvertor;
import com.kt.cloud.cop.module.git.GitCreate;
import com.kt.cloud.cop.module.git.GitReposInfo;
import com.kt.cloud.cop.module.git.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class GitPushTask {

    @Autowired
    private GitService gitService;

    @Async("taskExecutor")
    public void push(CodeProjectCreateCmd codeProjectCmd, File codeProject) {

        // 尝试生成Git仓库
        try {
            String gitReposUrl = attemptCreateGitRepos(codeProjectCmd, codeProject);

        } finally {
            attemptDeleteTempFileAfterGen(codeProjectCmd.getDeleteTempFileAfterGen(), codeProject);
        }
    }

    private void attemptDeleteTempFileAfterGen(Boolean delete, File codeProject) {
        if (delete) {
            FileUtil.del(codeProject);
        }
    }

    private String attemptCreateGitRepos(CodeProjectCreateCmd codeProjectCmd, File codeProject) {
        String gitReposUrl = "";
        if (codeProjectCmd.getReposSource().equals(ReposSourceEnums.CREATE_NEW.getValue())) {
            GitCreate gitCreate = CodeProjectConvertor.convertToGitCreate(codeProjectCmd);
            GitReposInfo gitReposInfo = gitService.createRepository(gitCreate);
            gitReposUrl = gitReposInfo.getReposUrl();
            gitService.intiAndPushToRepos(codeProject, gitReposInfo);
        }
        return gitReposUrl;
    }
}
