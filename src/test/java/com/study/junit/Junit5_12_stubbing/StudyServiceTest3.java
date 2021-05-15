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
import static org.mockito.Mockito.when;


// 연습문제
@ExtendWith(MockitoExtension.class)
class StudyServiceTest3 {

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

        Study study = new Study(10, "테스트");

        // memberService 객체에 findById 메소드를 1L 값으로 호출하면 Optional.of(member) 객체를 리턴하도록 stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        // studyRepo 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴 하도록 stubbing
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);
        assertEquals(member, study.getOwner());

    }
}
