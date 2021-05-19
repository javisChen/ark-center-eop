package com.kt.cloud.cop.infrastructure;


import com.kt.component.exception.BizException;

public class Assert {

    public static void isTrue(boolean condition, BizEnums bizEnum) {
        if (condition) {
            throw new BizException(bizEnum.getCode(), bizEnum.getMsg());
        }
    }

    public static void isFalse(boolean condition, BizEnums bizEnum) {
        isTrue(!condition, bizEnum);
    }

}
