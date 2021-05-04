package com.study.junit.Junit5_02_조건에따라테스트실행하기;

import com.study.junit.domain.Study;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("WINDOWS, LINUX 테스트 케이스")
    @EnabledOnOs({OS.WINDOWS, OS.LINUX})
    void create_new_study1(){
        String test_env = System.getenv("TEST_ENV");
        System.out.println("WINDOWS, LINUX 테스트 케이스 ");

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println(test_env + " TEST CASE");

            Assumptions.assumeTrue("LOCAL".equalsIgnoreCase(test_env));
            Study study = new Study(100);
            assertThat(study.getLimit()).isGreaterThan(0);
        });

        assumingThat("PROD".equalsIgnoreCase(test_env), () -> {
            System.out.println(test_env + " TEST CASE");

            Assumptions.assumeTrue("PROD".equalsIgnoreCase(test_env));
            Study study = new Study(10);
            assertThat(study.getLimit()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("MAC 테스트 케이스")
    @EnabledOnOs(OS.MAC)
    void create_new_study2(){
        String test_env = System.getenv("TEST_ENV");
        System.out.println("MAC 테스트 케이스");

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println(test_env + " TEST CASE");

            Assumptions.assumeTrue("LOCAL".equalsIgnoreCase(test_env));
            Study study = new Study(100);
            assertThat(study.getLimit()).isGreaterThan(0);
        });

        assumingThat("PROD".equalsIgnoreCase(test_env), () -> {
            System.out.println(test_env + " TEST CASE");

            Assumptions.assumeTrue("PROD".equalsIgnoreCase(test_env));
            Study study = new Study(10);
            assertThat(study.getLimit()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("JRE 버전 테스트 케이스")
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9})
    void create_new_study3(){
        String test_env = System.getenv("TEST_ENV");
        System.out.println("JRE 버전 테스트 케이스");

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println(test_env + " TEST CASE");

            Assumptions.assumeTrue("LOCAL".equalsIgnoreCase(test_env));
            Study study = new Study(100);
            assertThat(study.getLimit()).isGreaterThan(0);
        });

        assumingThat("PROD".equalsIgnoreCase(test_env), () -> {
            System.out.println(test_env + " TEST CASE");

            Assumptions.assumeTrue("PROD".equalsIgnoreCase(test_env));
            Study study = new Study(10);
            assertThat(study.getLimit()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("EnabledIfEnvironmentVariable 테스트 케이스")
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    void create_new_study4(){
        String test_env = System.getenv("TEST_ENV");
        System.out.println("EnabledIfEnvironmentVariable 테스트 케이스");

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println(test_env + " TEST CASE");

            Assumptions.assumeTrue("LOCAL".equalsIgnoreCase(test_env));
            Study study = new Study(100);
            assertThat(study.getLimit()).isGreaterThan(0);
        });

        assumingThat("PROD".equalsIgnoreCase(test_env), () -> {
            System.out.println(test_env + " TEST CASE");

            Assumptions.assumeTrue("PROD".equalsIgnoreCase(test_env));
            Study study = new Study(10);
            assertThat(study.getLimit()).isGreaterThan(0);
        });
    }
}
