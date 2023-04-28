package com.suimz.quarkus.service;

import com.suimz.quarkus.bean.req.ChatReq;
import com.suimz.quarkus.bean.resp.ChatResp;
import com.suimz.quarkus.exception.BizException;
import com.suimz.quarkus.openai.OpenAiService;
import com.suimz.quarkus.openai.bean.ChatCompletionRequest;
import com.suimz.quarkus.openai.bean.ChatCompletionResult;
import com.suimz.quarkus.openai.bean.ChatMessage;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.Arrays;

/**
 * service  - 聊天
 *
 * @author suimz.com
 */
@Slf4j
@ApplicationScoped
public class ChatService {

    @RestClient
    private OpenAiService openAiService;

    /**
     * 聊天
     * @param uid 当前用户ID
     * @param req
     * @return
     */
    public ChatResp chat(Integer uid, ChatReq req) {
        try {
            // 请求 OpenAI 对话接口
            ChatCompletionResult result = openAiService.createChatCompletion(
                    ChatCompletionRequest.builder()
                            .model("gpt-3.5-turbo") // 对话模型
                            .temperature(1.0) // 0-2
                            .user(String.valueOf(uid)) // 可选
                            .messages(Arrays.asList(new ChatMessage("user", req.getText())))
                            .build()
            );
            return ChatResp.builder()
                    .text(result.getChoices().stream().findFirst().get().getMessage().getContent())
                    .build();
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

}
