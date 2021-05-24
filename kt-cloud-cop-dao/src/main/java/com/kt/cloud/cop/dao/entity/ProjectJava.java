package com.kt.cloud.cop.dao.entity;
import com.kt.component.db.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * <p>
 * Java工程表
 * </p>
 *
 * @author COP
 * @since 2021-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectJava extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 工程id，关联project_base.id
     */
    private Long projectId;

    /**
     * 工程类型 enums[SPRING_BOOT,SpringBoot,1;]
     */
    @TableField("`type`")
    private Boolean type;

    /**
     * 构建方式 enums[MAVEN,Maven,1 ;GRADLE,Gradle,2]
     */
    private Boolean buildMode;

    /**
     * 组标识名
     */
    private String groupId;

    /**
     * 制品标识名
     */
    private String artifactId;

    /**
     * 包名
     */
    private String packageName;

    /**
     * JDK版本
     */
    private String jdkVersion;


    @Getter
    @AllArgsConstructor
    public enum Type {
        SPRING_BOOT(1, "SpringBoot"),
        ;
        private final Integer value;
        private final String text;
    }

    @Getter
    @AllArgsConstructor
    public enum BuildMode {
        MAVEN(1 , "Maven"),
                GRADLE(2, "Gradle"),
        ;
        private final Integer value;
        private final String text;
    }





}
