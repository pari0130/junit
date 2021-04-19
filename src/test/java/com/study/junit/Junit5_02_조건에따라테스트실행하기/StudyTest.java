package com.study.junit.Junit5_02_조건에따라테스트실행하기;

import com.study.junit.Study;
import com.study.junit.StudyStatus;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 테스트")
    void create_new_study1(){

    }
}