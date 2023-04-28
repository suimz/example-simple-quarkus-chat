package com.suimz.quarkus.bean.resp;

import lombok.Builder;
import lombok.Getter;

/**
 * 响应参数 - 聊天
 *
 * @author suimz.com
 */
@Getter
@Builder
public class ChatResp {

    private String text;

}
