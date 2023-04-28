package com.suimz.quarkus.bean.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 请求参数 - 登录
 *
 * @author suimz.com
 */
@Data
public class LoginReq {

    @NotBlank(message = "请输入用户名")
    private String username;

    @NotBlank(message = "请输入密码")
    private String password;

}
