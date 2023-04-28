package com.suimz.quarkus.exception;

import jakarta.ws.rs.core.Response;

/**
 * 业务异常
 *
 * @author suimz.com
 */
public class UnauthorizedBizException extends BizException {

    public UnauthorizedBizException() {
        super("登录异常", Response.Status.UNAUTHORIZED);
    }

}
