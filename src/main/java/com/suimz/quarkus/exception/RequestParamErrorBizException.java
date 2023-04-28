package com.suimz.quarkus.exception;

import jakarta.ws.rs.core.Response;

/**
 * 业务异常
 *
 * @author suimz.com
 */
public class RequestParamErrorBizException extends BizException {

    public RequestParamErrorBizException() {
        super("请求参数有误", Response.Status.FORBIDDEN);
    }

}
