package com.suimz.quarkus.dao.repository;

import com.suimz.quarkus.dao.entity.Member;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;

/**
 * 数据库操作对象 - member
 *
 * @author suimz.com
 */
@ApplicationScoped
public class MemberRepository implements PanacheRepositoryBase<Member, Integer> {

    /**
     * 根据用户名查找
     * @param username 用户名
     * @return 用户
     */
    public Member findByUsername(String username){
        return find("username", username).firstResult();
    }

    /**
     * 用户名是否存在
     * @param username 用户名
     */
    public boolean isExistByUsername(String username){
        return count("username", username) > 0;
    }

    /**
     * 修改最后登录时间
     * @param id 用户ID
     * @param time 登录时间
     */
    public boolean updateLoginTime(Integer id, LocalDateTime time) {
        return update("latestLoginTime = ?1 where id = ?2", time, id) > 0;
    }
}