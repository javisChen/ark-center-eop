package com.kt.cloud.cop.infrastructure.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 错误状态码枚举
 * <p>
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为5为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 **/
@AllArgsConstructor
@Getter
public enum BizCodeEnum {

    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    INVALID_EXCEPTION(10001, "参数格式校验失败"),
    TO_MANY_REQUEST(10002, "请求流量过大，请稍后再试"),
    SMS_CODE_EXCEPTION(10002, "验证码获取频率太高，请稍后再试"),
    PARAMS_VALIDATION_ERROR(10003, "请求参数异常"),
    GET_REDIS_LOCK_FAIL(10010, "获取redis分布式锁失败"),

    // 50 预提表
    PRE_SETTLE_TASK_EXISTS(50001,"任务已存在"),
    PRE_SETTLE_TASK_NOT_EXIST(50002,"任务不存在"),
    PRE_SETTLE_EXPORT_NON_DATA(50003,"导出数据为空"),
    PRE_SETTLE_EXPORT_EXCEED_THE_LIMIT(50004,"导出数据超出限制"),

    // 51 付款单
    PAYMENT_FORM_SETTLE_NOT_FOUND(51001,"查询结算单数据失败"),
    PAYMENT_FORM_CHANNEL_ERROR(51002,"渠道商数据错误"),
    PAYMENT_FORM_SETTLE_STATUS_ERROR(51003,"结算单状态错误"),
    PAYMENT_FORM_NOT_FOUND(51004,"查询付款单失败"),
    PAYMENT_FORM_STATUS_ERROR(51005,"付款单状态错误"),

    // 52 CPS历史订单导入
    SETTLE_IMPORT_GET_FILE_FAIL(52001,"获取上传文件失败"),
    SETTLE_IMPORT_NON_DATA(52002,"上传文件没有数据"),
    SETTLE_IMPORT_FILE_NOT_SUPPORT(52003,"文件类型不支持"),
    SETTLE_IMPORT_COVER_NOT_SUPPORT(52004,"不支持覆盖原数据"),
    SETTLE_IMPORT_SETTLE_DETAIL_NOT_FOUND(52005,"查询结算明细失败"),

    ;

    private final int code;
    private final String msg;

}
