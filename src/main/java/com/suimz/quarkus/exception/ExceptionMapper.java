package com.suimz.quarkus.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.suimz.quarkus.bean.resp.R;
import io.quarkus.hibernate.validator.runtime.jaxrs.ResteasyReactiveViolationException;
import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.ForbiddenException;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

/**
 * 全局异常拦截处理
 *
 * @author suimz.com
 */
@Slf4j
@ApplicationScoped
public class ExceptionMapper {

    /**
     * 业务异常
     */
    @ServerExceptionMapper(BizException.class)
    public RestResponse<R> map(BizException e) {
        return RestResponse.status(e.getHttpStatus(), R.error(e.getStatus(), e.getMessage()));
    }

    /**
     * 接口不支持的请求参数类型
     */
    @ServerExceptionMapper(NotSupportedException.class)
    public RestResponse<R> map(NotSupportedException e) {
        return RestResponse.status(Response.Status.UNSUPPORTED_MEDIA_TYPE, R.error(Response.Status.UNSUPPORTED_MEDIA_TYPE));
    }

    /**
     * 接口不支持的 method
     */
    @ServerExceptionMapper(NotAllowedException.class)
    public RestResponse<R> map(NotAllowedException e) {
        return RestResponse.status(Response.Status.METHOD_NOT_ALLOWED, R.error(Response.Status.METHOD_NOT_ALLOWED));
    }

    /**
     * 接口请求参数格式化失败
     */
    @ServerExceptionMapper(InvalidFormatException.class)
    public RestResponse<R> map(InvalidFormatException e) {
        return RestResponse.status(Response.Status.BAD_REQUEST, R.error(Response.Status.BAD_REQUEST));
    }

    // 访问接口路径不存在
    @ServerExceptionMapper(NotFoundException.class)
    public RestResponse<R> map(NotFoundException e) {
        return RestResponse.status(Response.Status.NOT_FOUND, R.error(Response.Status.NOT_FOUND));
    }

    /**
     * 接口请求参数校验失败
     */
    @ServerExceptionMapper(ResteasyReactiveViolationException.class)
    public RestResponse<R> map(ResteasyReactiveViolationException e) {
        return RestResponse.status(Response.Status.BAD_REQUEST, R.error(Response.Status.BAD_REQUEST.getStatusCode(), e.getConstraintViolations().iterator().next().getMessage()));
    }

    /**
     * JWT 校验失败
     */
    @ServerExceptionMapper({UnauthorizedException.class, AuthenticationFailedException.class})
    public RestResponse<R> map(Throwable e) {
        return RestResponse.status(Response.Status.UNAUTHORIZED, R.error(Response.Status.UNAUTHORIZED));
    }

    /**
     * 身份权限不足
     */
    @ServerExceptionMapper(ForbiddenException.class)
    public RestResponse<R> map(ForbiddenException e) {
        return RestResponse.status(Response.Status.FORBIDDEN, R.error(Response.Status.FORBIDDEN));
    }

    /**
     * 其他异常
     */
    @ServerExceptionMapper(Exception.class)
    public RestResponse<R> map(Exception e) {
        log.error(e.getMessage(), e);
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, R.error());
    }
}
