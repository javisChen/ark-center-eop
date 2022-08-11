package com.kt.cloud.eop.module.codeproject.generate.work;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.kt.cloud.eop.dao.entity.ProjectBasic;
import com.kt.cloud.eop.dao.service.IProjectBasicService;
import com.kt.cloud.eop.manager.git.GitCreateReposResponse;
import com.kt.cloud.eop.module.codeproject.convertor.CodeProjectConvertor;
import com.kt.cloud.eop.module.codeproject.dto.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.module.codeproject.dto.enums.ReposSourceEnums;
import com.kt.cloud.eop.module.git.GitCreate;
import com.kt.cloud.eop.module.git.service.GitService;
import com.kt.component.cache.CacheService;
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
    private final CacheService cacheService;

    public GitPushTask(GitService gitService, IProjectBasicService iProjectBasicService, CacheService cacheService) {
        this.gitService = gitService;
        this.iProjectBasicService = iProjectBasicService;
        this.cacheService = cacheService;
    }

    //    @Async("taskExecutor")
    @Transactional(rollbackFor = Exception.class)
    public void push(Long projectBasicId, CodeProjectCreateCmd codeProjectCmd, File codeProject) {
        if (codeProjectCmd.getReposSource().equals(ReposSourceEnums.CREATE_NEW.getValue())) {
            try {
                // 创建Git仓库
                GitCreate gitCreate = CodeProjectConvertor.convertToGitCreate(codeProjectCmd);
                GitCreateReposResponse gitCreateReposResponse = gitService.createRepository(gitCreate);
                String gitReposUrl = gitCreateReposResponse.getSshUrl();
                if (StrUtil.isEmpty(gitReposUrl)) {
                    iProjectBasicService.updateReposStatus(projectBasicId, ProjectBasic.ReposStatus.FAIL);
                    return;
                }
                // 创建成功后把生成的工程代码推送到仓库中
                updateProjectGitInfo(projectBasicId, gitCreateReposResponse);
                boolean initResult = gitService.intiAndPushToRepos(codeProject, codeProjectCmd.getCode(), gitReposUrl);
                if (initResult) {
                    iProjectBasicService.updatePushStatus(projectBasicId, ProjectBasic.PushStatus.SUCCESS);
                } else {
                    iProjectBasicService.updatePushStatus(projectBasicId, ProjectBasic.PushStatus.FAIL);
                }
            } catch (Exception e) {
                log.error("推送GIT服务失败", e);
                throw ExceptionFactory.sysException(e.getMessage());
            } finally {
                if (codeProjectCmd.getDeleteTempFileAfterGen()) {
                    try {
                        FileUtil.del(codeProject);
                    } catch (IORuntimeException e) {
                        log.error("删除生成项目失败", e);
                    }
                }
            }
        }
    }

    private void updateProjectGitInfo(Long projectBasicId, GitCreateReposResponse gitCreateReposResponse) {
        LambdaUpdateWrapper<ProjectBasic> uw = new LambdaUpdateWrapper<>();
        uw.eq(ProjectBasic::getId, projectBasicId)
                .set(ProjectBasic::getReposStatus, ProjectBasic.ReposStatus.SUCCESS.getValue())
                .set(ProjectBasic::getGitHtmlUrl, gitCreateReposResponse.getHtmlUrl())
                .set(ProjectBasic::getGitSshUrl, gitCreateReposResponse.getSshUrl())
                .set(ProjectBasic::getGitHttpsUrl, gitCreateReposResponse.getCloneUrl());
        iProjectBasicService.update(uw);
    }

}
