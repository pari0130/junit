package com.study.junit.Junit5_04_커스텀태그;

import com.study.junit.domain.Study;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Log4j2
class StudyCustomTag {

    @FastTest
    void create_new_study1(){
        log.info("fast 테스트");
        Study study = new Study(100);
        assertThat(study.getLimit()).isGreaterThan(0);
    }

    @SlowTest
    void create_new_study2(){
        log.info("slow 테스트");
        Study study = new Study(100);
        assertThat(study.getLimit()).isGreaterThan(0);
    }
}
