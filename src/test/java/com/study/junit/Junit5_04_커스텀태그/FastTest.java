package com.study.junit.Junit5_04_커스텀태그;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 어노테이션을 사용한 코드가 어노테이션 코드를 런타임까지 유지해야 한다
@Test
@Tag("fast")
@DisplayName("스터디 fast")
public @interface FastTest {
}
