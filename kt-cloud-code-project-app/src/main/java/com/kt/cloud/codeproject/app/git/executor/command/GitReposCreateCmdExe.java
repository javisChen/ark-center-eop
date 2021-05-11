package com.kt.cloud.codeproject.app.git.executor.command;

import com.kt.cloud.codeproject.app.git.convertor.GitCmdConvertor;
import com.kt.cloud.codeproject.client.git.dto.command.GitReposCreateCmd;
import com.kt.cloud.codeproject.domain.git.gateway.GitGateway;
import com.kt.component.dto.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GitReposCreateCmdExe {

    @Autowired
    private GitGateway gateway;

    public ServerResponse execute(GitReposCreateCmd cmd) {
        gateway.createGitRepos(GitCmdConvertor.toDomainObject(cmd));
        return ServerResponse.ok();
    }
}
