package com.study.junit.Junit5_12_stubbing;

import com.study.junit.domain.Member;
import com.study.junit.domain.Study;
import com.study.junit.member.MemberNotFoundException;
import com.study.junit.member.MemberService;
import com.study.junit.study.StudyRepository;
import com.study.junit.study.StudyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


// MockitoExtension 을 확장모델로 받아야 @Mock 으로 서비스 객체를 얻을 수 있음
@ExtendWith(MockitoExtension.class)
class StudyServiceTest1 {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService() throws MemberNotFoundException {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("pari0130@gmail.com");

        // ArgumentMatchers any 사용 할 경우
        when(memberService.findById(any())).thenReturn(Optional.of(member));

        Study study = new Study(10, "java");
        studyService.createNewStudy(1L, study);

        assertEquals("pari0130@gmail.com", memberService.findById(1L).get().getEmail());
        assertEquals("pari0130@gmail.com", memberService.findById(2L).get().getEmail());

        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        // 예외를 던저라, when 1L id 일떄
        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });

        memberService.validate(2L);

    }
}
