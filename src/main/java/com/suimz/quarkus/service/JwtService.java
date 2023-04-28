package com.suimz.quarkus.service;

import com.suimz.quarkus.constans.JwtConst;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Service - JWT
 *
 * @author suimz.com
 */
@ApplicationScoped
public class JwtService {

    /**
     * 生成token
     * @param uid 用户ID
     * @return token
     */
    public String sign(int uid) {
        return Jwt.claim(JwtConst.UID, String.valueOf(uid)).sign();
    }

}
