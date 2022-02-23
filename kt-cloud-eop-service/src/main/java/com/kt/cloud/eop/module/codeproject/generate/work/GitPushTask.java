package com.kt.cloud.eop.module.codeproject.generate.work;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.kt.cloud.eop.api.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.api.codeproject.enums.ReposSourceEnums;
import com.kt.cloud.eop.dao.entity.ProjectBasic;
import com.kt.cloud.eop.dao.service.IProjectBasicService;
import com.kt.cloud.eop.module.codeproject.convertor.CodeProjectConvertor;
import com.kt.cloud.eop.module.git.GitCreate;
import com.kt.cloud.eop.module.git.GitReposInfo;
import com.kt.cloud.eop.module.git.service.GitService;
import com.kt.component.cache.redis.RedisService;
import com.kt.component.exception.ExceptionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Component
@EnableAsync
@Slf4j
public class GitPushTask {

    private final GitService gitService;
    private final IProjectBasicService iProjectBasicService;
    private final RedisService redisService;

    public GitPushTask(GitService gitService, IProjectBasicService iProjectBasicService, RedisService redisService) {
        this.gitService = gitService;
        this.iProjectBasicService = iProjectBasicService;
        this.redisService = redisService;
    }

//    @Async("taskExecutor")
    @Transactional(rollbackFor = Exception.class)
    public void push(Long projectBasicId, CodeProjectCreateCmd codeProjectCmd, File codeProject) {
        if (codeProjectCmd.getReposSource().equals(ReposSourceEnums.CREATE_NEW.getValue())) {
            try {
                // 创建Git仓库
                GitCreate gitCreate = CodeProjectConvertor.convertToGitCreate(codeProjectCmd);
                GitReposInfo gitReposInfo = gitService.createRepository(gitCreate);
                String gitReposUrl = gitReposInfo.getReposUrl();
                if (StrUtil.isEmpty(gitReposUrl)) {
                    iProjectBasicService.updateReposStatus(projectBasicId, ProjectBasic.ReposStatus.FAIL);
                    return;
                }

                // 创建成功后把生成的工程代码推送到仓库中
                iProjectBasicService.updateReposStatusAndReposUrl(projectBasicId, ProjectBasic.ReposStatus.SUCCESS, gitReposUrl);
                boolean initResult = gitService.intiAndPushToRepos(codeProject, codeProjectCmd.getCode(), gitReposUrl);
                if (!initResult) {
                    iProjectBasicService.updatePushStatus(projectBasicId,ProjectBasic.PushStatus.FAIL);
                } else {
                    iProjectBasicService.updatePushStatus(projectBasicId,ProjectBasic.PushStatus.SUCCESS);
                }
            } catch (Exception e) {
                log.error("推送GIT服务失败", e);
                throw ExceptionFactory.bizException(e.getMessage());
            } finally {
                if (codeProjectCmd.getDeleteTempFileAfterGen()) {
                    FileUtil.del(codeProject);
                }
            }
        }
    }

}
