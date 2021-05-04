package com.study.junit.Junit5_01_Assertion;

import com.study.junit.domain.Study;
import com.study.junit.study.StudyStatus;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// 테스트 이름 표시 (언더바를 바꿔줌)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

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
    @DisplayName("테스트 ALL")
    void create_new_study2(){

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = ex.getMessage();
        assertEquals("limit 은 0보다 커야한다.", message);

        Study study = new Study(-10);

        // 3개의 테스트를 진행할때 첫번째 테스트 통과가 안되면 다음 테스트 케이스를 알 수 없기 때문에
        // 3개의 테스트를 asertAll 로 한번에 묶을 수 있다.
        assertAll(
                () -> assertNotNull(study),
                () -> // DRAFT 가 null 이므로 에러 체크가 된다
                      // 테스트 실패시 출력할 메세지를 함수로 리턴
                      // 문자열 연산의 비용을 줄이기 위해 람다 함수 형식으로 메세지 리턴을 사용
                      Assertions.assertEquals(StudyStatus.DRAFT, study.getStatus1(), () -> "check 1 : 스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT + " 여야 한다."),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus2(), () -> "check 2 : 스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT + " 여야 한다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.")
        );

        System.out.println("create");
    }
}
