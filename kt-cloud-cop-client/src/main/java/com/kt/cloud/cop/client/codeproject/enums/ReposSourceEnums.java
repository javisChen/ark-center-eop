package com.kt.cloud.cop.client.codeproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ReposSourceEnums {

    CREATE_NEW(1, "创建新仓库"),
    BIND(2, "关联已有仓库"),
    ;
    private final Integer value;
    private final String text;

    public static ReposSourceEnums getByValue(Integer value) {
        return Arrays.stream(values())
                .filter(enums -> enums.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    public static String getText(Integer value) {
        return Optional.ofNullable(getByValue(value))
                .map(ReposSourceEnums::getText)
                .orElse("");
    }
}