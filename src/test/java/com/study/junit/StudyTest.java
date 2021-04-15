package com.study.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Test
    void create(){
        Study study = new Study();
        assertNotNull(study);

        System.out.println("create");
    }

//    @BeforeAll
//    void create1(){
//        System.out.println("create1");
//    }
//
//    @AfterAll
//
//    @BeforeEach
//
//    @AfterEach
}