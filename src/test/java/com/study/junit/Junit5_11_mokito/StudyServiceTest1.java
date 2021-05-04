package com.study.junit.Junit5_11_mokito;

import com.study.junit.domain.Member;
import com.study.junit.domain.Study;
import com.study.junit.member.MemberNotFoundException;
import com.study.junit.member.MemberService;
import com.study.junit.study.StudyRepository;
import com.study.junit.study.StudyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


// MockitoExtension 을 확장모델로 받아야 @Mock 으로 서비스 객체를 얻을 수 있음
@ExtendWith(MockitoExtension.class)
class StudyServiceTest1 {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    /*
    * 구현체는 없지만 인터페이스만 알고 있고 인터페이스 기반으로 코드를 작성 중임
    * 코드가 정상적으로 작동하는지 보려고 하는 행동 mocking
    * */
    @Test
    void createStudyService() {
        // mock 을 이용해여 service, repo 를 주입할 수 있음
        // mock 객체를 만듦
        // MemberService memberService = mock(MemberService.class);
        // StudyRepository studyRepository = mock(StudyRepository.class);

        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }
}
