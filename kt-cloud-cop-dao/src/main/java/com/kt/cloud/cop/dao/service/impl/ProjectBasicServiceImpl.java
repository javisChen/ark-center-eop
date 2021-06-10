package com.kt.cloud.cop.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.kt.cloud.cop.dao.entity.ProjectBasic;
import com.kt.cloud.cop.dao.mapper.ProjectBasicMapper;
import com.kt.cloud.cop.dao.service.IProjectBasicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工程表 服务实现类
 * </p>
 *
 * @author COP
 * @since 2021-06-10
 */
@Service
public class ProjectBasicServiceImpl extends ServiceImpl<ProjectBasicMapper, ProjectBasic> implements IProjectBasicService {

    @Override
    public void updatePushStatus(Long id, ProjectBasic.PushStatus pushStatus) {
        LambdaUpdateWrapper<ProjectBasic> uw = new LambdaUpdateWrapper<>();
        uw.eq(ProjectBasic::getId, id)
                .set(ProjectBasic::getPushStatus, pushStatus.getValue());
        this.update(uw);
    }

    @Override
    public void updateReposStatus(Long id, ProjectBasic.ReposStatus reposStatus) {
        LambdaUpdateWrapper<ProjectBasic> uw = new LambdaUpdateWrapper<>();
        uw.eq(ProjectBasic::getId, id)
                .set(ProjectBasic::getReposStatus, reposStatus.getValue());
        this.update(uw);
    }

    @Override
    public void updateReposStatusAndReposUrl(Long id, ProjectBasic.ReposStatus reposStatus, String reposUrl) {
        LambdaUpdateWrapper<ProjectBasic> uw = new LambdaUpdateWrapper<>();
        uw.eq(ProjectBasic::getId, id)
                .set(ProjectBasic::getReposStatus, reposStatus.getValue())
                .set(ProjectBasic::getGitReposUrl, reposUrl);
        this.update(uw);
    }
}
