package com.kt.cloud.cop.dao.entity;
import com.kt.component.db.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * <p>
 * 工程表
 * </p>
 *
 * @author COP
 * @since 2021-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectBasic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 工程名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 工程代码
     */
    @TableField("`code`")
    private String code;

    /**
     * 工程介绍
     */
    private String description;

    /**
     * 工程类型 enums[BACKEND,后端应用,1 ;FRONTEND,前端应用,2]
     */
    @TableField("`type`")
    private Integer type;

    /**
     * Git仓库地址
     */
    private String gitReposUrl;


    @Getter
    @AllArgsConstructor
    public enum Type {
        BACKEND(1 , "后端应用"),
        FRONTEND(2, "前端应用")
        ;
        private final Integer value;
        private final String text;
    }


}
