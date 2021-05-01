package com.study.junit.Junit5_03_태깅과필터링;

import com.study.junit.Study;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Log4j2
class StudyTag {

    @Test
    @DisplayName("스터디 fast")
    @Tag("fast")
    void create_new_study1(){
        log.info("fast 테스트");
        Study study = new Study(100);
        assertThat(study.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 slow")
    @Tag("slow")
    void create_new_study2(){
        log.info("slow 테스트");
        Study study = new Study(10);
        assertThat(study.getLimit()).isGreaterThan(0);
    }
}
