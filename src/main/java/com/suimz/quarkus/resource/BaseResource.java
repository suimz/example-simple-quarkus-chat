package com.suimz.quarkus.resource;

import com.suimz.quarkus.constans.JwtConst;
import com.suimz.quarkus.exception.UnauthorizedBizException;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 * Resource 基类
 *
 * @author suimz.com
 */
public class BaseResource {

    @Inject
    protected JsonWebToken jwt;

    /**
     * 获取当前用户ID
     */
    protected Integer getUid() {
        try {
            return Integer.valueOf(jwt.getClaim(JwtConst.UID));
        } catch (Exception e) {
            throw new UnauthorizedBizException();
        }
    }

}
