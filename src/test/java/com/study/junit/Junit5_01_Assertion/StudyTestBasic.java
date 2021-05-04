package com.study.junit.Junit5_01_Assertion;

import com.study.junit.domain.Study;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// 테스트 이름 표시 (언더바를 바꿔줌)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTestBasic {

    @Test
    @DisplayName("예외처리 비교")
    void create_new_study1(){
        // exception 을 체크 후 기대한 메세지 값과 같은지 체크 하는 방법
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = ex.getMessage();
        assertEquals("limit 은 0보다 커야한다.", message);
        System.out.println("create");
    }

    @Test
    @Disabled
    void create1(){
        Study study = new Study();
        assertNotNull(study);

        System.out.println("create1");
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
