package com.suimz.quarkus.openai;

import com.suimz.quarkus.openai.bean.ChatCompletionRequest;
import com.suimz.quarkus.openai.bean.ChatCompletionResult;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * Open AI 接口
 *
 * @author suimz.com
 */
@RegisterRestClient(configKey="openai-api")
public interface OpenAiService {

    @POST
    @Path("/v1/chat/completions")
    ChatCompletionResult createChatCompletion(ChatCompletionRequest request);

}