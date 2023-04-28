package com.suimz.quarkus.resource;

import com.suimz.quarkus.bean.req.ChatReq;
import com.suimz.quarkus.bean.resp.ChatResp;
import com.suimz.quarkus.bean.resp.R;
import com.suimz.quarkus.exception.RequestParamErrorBizException;
import com.suimz.quarkus.service.ChatService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * REST API - 聊天
 *
 * @author suimz.com
 */
@Path("/api/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource extends BaseResource {

    @Inject
    private ChatService chatService;

    /**
     * 聊天
     */
    @POST
    @Authenticated // 必须通过身份认证
    public R<ChatResp> chat(@Valid ChatReq req) {
        if (req == null) throw new RequestParamErrorBizException();
        ChatResp resp = chatService.chat(getUid(), req);
        return R.ok(resp);
    }

}
