package com.ark.center.eop.dao.service;

import com.ark.center.eop.dao.entity.ProjectBasic;
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
