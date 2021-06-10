package com.kt.cloud.cop.dao.service;

import com.kt.cloud.cop.dao.entity.ProjectBasic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工程表 服务类
 * </p>
 *
 * @author COP
 * @since 2021-06-10
 */
public interface IProjectBasicService extends IService<ProjectBasic> {

    void updatePushStatus(Long id, ProjectBasic.PushStatus pushStatus);

    void updateReposStatus(Long id, ProjectBasic.ReposStatus reposStatus);

    void updateReposStatusAndReposUrl(Long id, ProjectBasic.ReposStatus reposStatus, String reposUrl);
}
