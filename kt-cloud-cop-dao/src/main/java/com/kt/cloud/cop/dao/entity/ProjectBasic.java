package com.kt.cloud.cop.dao.entity;
import com.kt.component.db.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.kt.component.common.enums.BasicEnums;
import com.kt.component.common.enums.EnumUtils;
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
 * @since 2021-06-10
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

    /**
     * 工程的独有属性JSON
     */
    private String extProperties;

    /**
     * 代码推送状态 enums[NOT_PUSH,未推送,0;SUCCESS,推送成功,1;FAIL,推送失败,2;]
     */
    private Integer pushStatus;

    /**
     * 仓库状态 enums[NOT_CREATE,未推送,0;SUCCESS,创建成功,1;FAIL,创建失败,2;]
     */
    private Integer reposStatus;

    /**
     * 删除标识 0-表示未删除 大于0-已删除
     */
    private Long isDeleted;

    @Getter
    @AllArgsConstructor
    public enum Type implements BasicEnums {
        BACKEND(1 , "后端应用"),
                FRONTEND(2, "前端应用"),
        ;
        private final Integer value;
        private final String text;

        public static Type getByValue(Integer value) {
            return EnumUtils.getByValue(values(), value);
        }

        public static String getText(Integer value) {
            return EnumUtils.getTextByValue(values(), value);
        }
    }
    @Getter
    @AllArgsConstructor
    public enum Scaffold implements BasicEnums {
        SpringCloud(1, "SpringCloud"),
        ;
        private final Integer value;
        private final String text;

        public static Scaffold getByValue(Integer value) {
            return EnumUtils.getByValue(values(), value);
        }

        public static String getText(Integer value) {
            return EnumUtils.getTextByValue(values(), value);
        }
    }
    @Getter
    @AllArgsConstructor
    public enum PushStatus implements BasicEnums {
        NOT_PUSH(0, "未推送"),
                SUCCESS(1, "推送成功"),
                FAIL(2, "推送失败"),
        ;
        private final Integer value;
        private final String text;

        public static PushStatus getByValue(Integer value) {
            return EnumUtils.getByValue(values(), value);
        }

        public static String getText(Integer value) {
            return EnumUtils.getTextByValue(values(), value);
        }
    }
    @Getter
    @AllArgsConstructor
    public enum ReposStatus implements BasicEnums {
        NOT_CREATE(0, "未推送"),
                SUCCESS(1, "创建成功"),
                FAIL(2, "创建失败"),
        ;
        private final Integer value;
        private final String text;

        public static ReposStatus getByValue(Integer value) {
            return EnumUtils.getByValue(values(), value);
        }

        public static String getText(Integer value) {
            return EnumUtils.getTextByValue(values(), value);
        }
    }
}
