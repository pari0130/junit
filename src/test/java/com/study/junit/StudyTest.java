package com.study.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// 테스트 이름 표시 (언더바를 바꿔줌)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기 학습")
    void create_new_study(){
        Study study = new Study();
        assertNotNull(study);

        System.out.println("create");
    }

    @Test
    @Disabled
    void create1(){
        Study study = new Study();
        assertNotNull(study);

        System.out.println("");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("가장먼저");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("AfterAll");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("@Test 전");
    }

    @AfterEach
    void afterEach(){
        System.out.println("@Test 후");
    }
}