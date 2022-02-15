package com.kt.cloud.eop.client.codeproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum GenerateModeEnums {

    SCAFFOLD(1, "基于脚手架生成器"),
    ;
    private final Integer value;
    private final String text;

    public static GenerateModeEnums getByValue(Integer value) {
        return Arrays.stream(values())
                .filter(enums -> enums.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    public static String getText(Integer value) {
        return Optional.ofNullable(getByValue(value))
                .map(GenerateModeEnums::getText)
                .orElse("");
    }
}