package com.kt.cloud.cop.manager.git;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GitApiException extends RuntimeException {

    private Integer statusCode;

    public GitApiException(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public GitApiException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public GitApiException(Integer statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
