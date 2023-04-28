package com.suimz.quarkus.bean.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 请求参数 - 聊天
 *
 * @author suimz.com
 */
@Data
public class ChatReq {

    @NotBlank(message = "请输入聊天内容")
    private String text;

}
