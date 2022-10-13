package com.ark.center.eop.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BizEnums {

    /**
     * 定义范围 -> A1001 ~ A2000
     */
    UNKNOWN_EXCEPTION("A1001", ""),
    ;

    private final String code;
    private final String msg;

}
