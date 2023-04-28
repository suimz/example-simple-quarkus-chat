package com.suimz.quarkus.bean.resp;

import jakarta.ws.rs.core.Response;
import lombok.Builder;
import lombok.Data;

/**
 * API响应结构体
 *
 * @author suimz.com
 */
@Builder
@Data
public class R<T> {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 状态描述信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static R ok() {
        return R.<Void>ok(null);
    }

    public static <T> R<T> ok(T data) {
        return R.<T>builder().status(200).message("success").data(data).build();
    }

    public static R error() {
        return R.error(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "服务异常，请稍后再试");
    }

    public static R error(Response.Status status) {
        return R.error(status.getStatusCode(), status.getReasonPhrase());
    }

    public static R error(String error) {
        return R.error(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), error);
    }

    public static R error(Integer status, String error) {
        return R.<Void>builder()
                .status(status)
                .message(error)
                .build();
    }

}