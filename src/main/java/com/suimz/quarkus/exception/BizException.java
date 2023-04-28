package com.suimz.quarkus.exception;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

/**
 * 业务异常
 *
 * @author suimz.com
 */
@Getter
public class BizException extends RuntimeException {

    /**
     * 异常绑定的HTTP响应状态码， 默认为200
     */
    private Response.Status httpStatus = Response.Status.OK;

    /**
     * 接口返回json结构中的状态标识
     */
    private int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();

    public BizException(String msg) {
        super(msg);
    }

    public BizException(String msg, Response.Status httpStatus) {
        this(msg, httpStatus.getStatusCode());
        this.httpStatus = httpStatus;
    }

    public BizException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }

}
