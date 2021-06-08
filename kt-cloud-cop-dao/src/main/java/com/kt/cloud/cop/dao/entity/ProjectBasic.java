package com.kt.cloud.cop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.kt.component.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * 工程表
 * </p>
 *
 * @author COP
 * @since 2021-06-03
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
     * 脚手架 enums[SpringCloud,SpringCloud,1;]
     */
    private Integer scaffold;

    /**
     * Git仓库地址
     */
    private String gitReposUrl;




    @Getter
    @AllArgsConstructor
    public enum Type {
        BACKEND(1 , "后端应用"),
                FRONTEND(2, "前端应用"),
        ;
        private final Integer value;
        private final String text;

        public static Type getByValue(Integer value) {
            return Arrays.stream(values())
                    .filter(enums -> enums.getValue().equals(value))
                    .findFirst()
                    .orElse(null);
        }

        public static String getText(Integer value) {
            return Optional.ofNullable(getByValue(value))
                    .map(Type::getText)
                    .orElse("");
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Scaffold {
        SpringCloud(1, "SpringCloud"),
        ;
        private final Integer value;
        private final String text;

        public static Scaffold getByValue(Integer value) {
            return Arrays.stream(values())
                    .filter(enums -> enums.getValue().equals(value))
                    .findFirst()
                    .orElse(null);
        }

        public static String getText(Integer value) {
            return Optional.ofNullable(getByValue(value))
                    .map(Scaffold::getText)
                    .orElse("");
        }
    }


}
