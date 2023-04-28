package com.suimz.quarkus.dao.entity;

import com.suimz.quarkus.dao.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 表实体 - member
 *
 * @author suimz.com
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "member") // 指定表名
public class Member {
    @Id
    // GenerationType.IDENTITY 表示使用数据库的自增主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    // 将字段转为枚举
    @Convert(converter = MemberStatus.Converter.class)
    private MemberStatus status;

    @Column(name = "latest_login_time")
    private LocalDateTime latestLoginTime;
}