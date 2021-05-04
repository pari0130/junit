package com.study.junit.Junit5_01_Assertion;

import com.study.junit.domain.Study;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTestTimeout {

    @Test
    @DisplayName("테스트 타임아웃")
    void create_new_study(){

        // 타임아웃 시간 동안만 체크 하므로 100 m 까지만 기다린다.
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(1000);
        });

        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(1000);
        });
    }
}
