package com.study.junit.Junit5_06_테스트인스턴스;

import com.study.junit.domain.Study;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // test instance 를 달아줌으로서 1번만 생성되므로 Test class 에 static 이 필요없다.
class StudyInstance {

    int value = 1;

    @BeforeAll
    @Test
    void test(){
        log.info("before all");
    }

    @DisplayName("반복 테스트 1")
    @RepeatedTest(10)
    void create_new_study1(){
        log.info("테스트 인스턴스");
        value++;
        Study study = new Study(value);
        log.info("limit : " + study.getLimit());
    }
}
