package com.suimz.quarkus.resource;

import com.suimz.quarkus.bean.req.LoginReq;
import com.suimz.quarkus.bean.resp.R;
import com.suimz.quarkus.exception.RequestParamErrorBizException;
import com.suimz.quarkus.service.MemberService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * REST API - 用户
 *
 * @author suimz.com
 */
@Path("/api/member")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MemberResource extends BaseResource {

    @Inject
    private MemberService memberService;

    /**
     * 用户注册
     * @param req
     */
    @POST
    @Path("/register")
    public R<Void> register(@Valid LoginReq req) {
        if (req == null) throw new RequestParamErrorBizException();
        memberService.register(req.getUsername(), req.getPassword());
        return R.ok();
    }

    /**
     * 用户登录
     * @param req
     * @return 用户 token
     */
    @POST
    @Path("/login")
    public R<String> login(@Valid LoginReq req) {
        if (req == null) throw new RequestParamErrorBizException();
        return R.ok(memberService.login(req.getUsername(), req.getPassword()));
    }

}