package com.liumd.data.utils.exceptionUtil;

import lombok.Data;

/**
 * @author liumuda
 * @date 2022/1/14 9:22
 */
@Data
public class ErrorInfo {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public ErrorInfo() {
    }

    public ErrorInfo(String message) {
        this.message = message;
    }

    public ErrorInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
