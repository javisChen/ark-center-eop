package com.kt.cloud.cop.infrastructure.validator;


import com.kt.component.dto.ResponseEnums;
import com.kt.component.exception.BizException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

/**
 * 基础验证器
 **/
public abstract class BaseValidator {
    public static void validateCondition(boolean bool, String message) {
        if (bool) {
            throw new BizException(ResponseEnums.USER_METHOD_ARGUMENT_NOT_VALID.getCode(), message);
        }
    }

    public static void validateRange(Integer num, Integer min, Integer max, String name) {
        validateCondition(num == null, name + "不能为空");
        if (min != null) {
            validateCondition(num < min, name + "不能小于" + min);
        }
        if (max != null) {
            validateCondition(num > max, name + "不能大于" + max);
        }
    }

    public static void validatePositive(Integer num, String name) {
        validateCondition(num == null, name + "不能为空");
        validateCondition(num <= 0, name + "不能小于等于0");
    }

    public static void validatePositive(Long num, String name) {
        validateCondition(num == null, name + "不能为空");
        validateCondition(num <= 0, name + "不能小于等于0");
    }

    public static void validatePositive(BigDecimal bigDecimal, String name) {
        validateCondition(bigDecimal == null, name + "不能为空");
        validateCondition(bigDecimal.compareTo(BigDecimal.ZERO) <= 0, name + "不能小于等于0");
    }

    public static void stringNotBlank(String str, String message) {
        validateCondition(StringUtils.isBlank(str), message);
    }

    public static void isNull(Object obj, String message) {
        validateCondition(Objects.isNull(obj), message);
    }

    public static void nonNull(Object obj, String message) {
        validateCondition(Objects.nonNull(obj), message);
    }

    public static void stringNotBlankNullable(String str, String message) {
        if (str != null) {
            validateCondition(StringUtils.isBlank(str), message);
        }
    }

    public static <T> void collectionNotEmpty(Collection<T> collection, String message) {
        validateCondition(collection == null, message);
        validateCondition(collection.size() == 0, message);
    }
}
