package com.study.junit.Junit5_05_테스트반복하기1부;

import com.study.junit.domain.Study;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Log4j2
class StudyRepeat {

    @DisplayName("반복 테스트 1")
    @RepeatedTest(10)
    void create_new_study1(RepetitionInfo repetitionInfo){
        log.info("반복 " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
        Study study = new Study(100);
        assertThat(study.getLimit()).isGreaterThan(0);
    }

    @DisplayName("반복 테스트 2")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void create_new_study2(RepetitionInfo repetitionInfo){
        log.info("반복 " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
        Study study = new Study(100);
        assertThat(study.getLimit()).isGreaterThan(0);
    }

    @DisplayName("반복 테스트 3")
    @ParameterizedTest(name = "{index} {displayName} message{0}") // 파라미터를 정의하는 어노테이션
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
    void create_new_study3(String message){
        log.info(message);
    }
}
