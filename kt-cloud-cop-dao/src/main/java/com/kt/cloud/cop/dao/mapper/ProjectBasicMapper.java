package com.kt.cloud.cop.dao.mapper;

import com.kt.cloud.cop.dao.entity.ProjectBasic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 工程表 Mapper 接口
 * </p>
 *
 * @author COP
 * @since 2021-05-21
 */
public interface ProjectBasicMapper extends BaseMapper<ProjectBasic> {

    List<ProjectBasic> selectList();
}
