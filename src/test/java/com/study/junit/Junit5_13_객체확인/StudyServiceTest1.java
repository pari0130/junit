package com.study.junit.Junit5_13_객체확인;

import com.study.junit.domain.Member;
import com.study.junit.domain.Study;
import com.study.junit.member.MemberNotFoundException;
import com.study.junit.member.MemberService;
import com.study.junit.study.StudyRepository;
import com.study.junit.study.StudyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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

        Study study = new Study(10, "테스트");

        // memberService 객체에 findById 메소드를 1L 값으로 호출하면 Optional.of(member) 객체를 리턴하도록 stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        // studyRepo 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴 하도록 stubbing
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);
        assertEquals(member, study.getOwner());

        // verify 를 사용해서 mock 객체 확인
        // memberService 에 의해 1번이라도 호출이 되었는지 체크크
        verify(memberService, times(1)).notify(study);
        verify(memberService, times(1)).notify(member);
        verify(memberService, never()).validate(any());

        // study noti 가 호출되고 member 가 호출되어야 하는지 체크 하는 방법
        InOrder inOrder = inOrder(memberService);

        // 이 경우 memberService 의 member 가 먼저 체크되므로 에러가 난다.
//        inOrder.verify(memberService).notify(member);
//        inOrder.verify(memberService).notify(study);

        // 아래와 같이 호출 순서가 맞을 경우는 정상
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);

        // 더이상 호출이 안되도록 체크 하는 방법
        verifyNoMoreInteractions(memberService);
    }
}
