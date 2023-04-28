package com.suimz.quarkus.exception;

import jakarta.ws.rs.core.Response;

/**
 * 业务异常
 *
 * @author suimz.com
 */
public class AccountProhibitBizException extends BizException {

    public AccountProhibitBizException() {
        super("该账号已被封禁", Response.Status.FORBIDDEN);
    }

}
