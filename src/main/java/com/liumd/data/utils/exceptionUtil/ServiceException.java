package com.liumd.data.utils.exceptionUtil;

/**
 * @author liumuda
 * @date 2022/1/14 9:15
 */
public class ServiceException extends RuntimeException {
    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
