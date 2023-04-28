package com.suimz.quarkus.dao.enums;

import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

/**
 * 枚举 - 用户状态
 *
 * @author suimz.com
 */
@Getter
@AllArgsConstructor
public enum MemberStatus {
    // 禁止
    PROHIBIT(0),
    // 正常
    NORMAL(1),
    ;

    private int value;

    public static MemberStatus get(Integer value) {
        return value == null ? null : Arrays.stream(values())
                .filter(item -> item.getValue() == value)
                .findFirst().orElse(null);
    }

    /**
     * 转换器 - 数据库字段的值映射为枚举对象
     */
    public static class Converter implements AttributeConverter<MemberStatus, Integer> {
        @Override
        public Integer convertToDatabaseColumn(MemberStatus attribute) {
            return attribute == null ? null : attribute.value;
        }

        @Override
        public MemberStatus convertToEntityAttribute(Integer dbData) {
            return MemberStatus.get(dbData);
        }
    }
}