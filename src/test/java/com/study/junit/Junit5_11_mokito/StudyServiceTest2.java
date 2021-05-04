package com.study.junit.Junit5_11_mokito;

import com.study.junit.member.MemberService;
import com.study.junit.study.StudyRepository;
import com.study.junit.study.StudyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


// MockitoExtension 을 확장모델로 받아야 @Mock 으로 서비스 객체를 얻을 수 있음
@ExtendWith(MockitoExtension.class)
class StudyServiceTest2 {

    // @Mock 을 인자로 받아서 주입 할 수 있음
    @Test
    void createStudyService(@Mock MemberService memberService,@Mock StudyRepository studyRepository){
        // mock 을 이용해여 service, repo 를 주입할 수 있음
        // mock 객체를 만듦
        // MemberService memberService = mock(MemberService.class);
        // StudyRepository studyRepository = mock(StudyRepository.class);

        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }
}
