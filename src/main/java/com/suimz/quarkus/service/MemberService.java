package com.suimz.quarkus.service;

import com.suimz.quarkus.dao.entity.Member;
import com.suimz.quarkus.dao.repository.MemberRepository;
import com.suimz.quarkus.exception.BizException;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

/**
 * service  - 用户
 *
 * @author suimz.com
 */
@ApplicationScoped
public class MemberService {

    @Inject
    private MemberRepository memberRepository;
    @Inject
    private JwtService jwtService;

    /**
     * 用户注册
     * @param username
     * @param password
     */
    @Transactional
    public void register(String username, String password) {
        // 是否已被注册
        if (memberRepository.isExistByUsername(username))
            throw new BizException("该账号已被注册");

        Member member = Member.builder()
                .username(username)
                .password(BcryptUtil.bcryptHash(password))
                .build();

        // 入库
        memberRepository.persistAndFlush(member);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 用户 token
     */
    @Transactional
    public String login(String username, String password) {
        // 从库中查找出用户
        Member member = memberRepository.findByUsername(username);
        if (member == null)
            throw new BizException("用户不存在", Response.Status.NOT_FOUND);

        // 校验密码
        if (!BcryptUtil.matches(password, member.getPassword()))
            throw new BizException("密码或账号有误", Response.Status.FORBIDDEN);

        // 生成token
        String token = jwtService.sign(member.getId());

        // 记录登录时间
        memberRepository.updateLoginTime(member.getId(), LocalDateTime.now());

        return token;
    }
}
