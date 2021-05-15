package com.study.junit.Junit5_15_Mock_Test;

import com.study.junit.domain.Member;
import com.study.junit.domain.Study;
import com.study.junit.member.MemberNotFoundException;
import com.study.junit.member.MemberService;
import com.study.junit.study.StudyRepository;
import com.study.junit.study.StudyService;
import com.study.junit.study.StudyStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class MockTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    @DisplayName("Bdd")
    void createStudyService() throws MemberNotFoundException {
        // given ===========================================
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "테스트");
        assertNull(study.getOpenedDateTime());

        // studyRepo mock 객체의 save 메소드 호출 시 study를 리턴하도록 만들기
        // save 로 study 가 호출이 될 경우 체크
        given(studyRepository.save(study)).willReturn(study);

        // when ===========================================
        studyService.openStudy(study);

        // then ===========================================
        // study의 status가 opened 로 변경됐는지 확인
        assertEquals(StudyStatus.OPENED, study.getStatus());

        // study의 openedDateTime이 null이 아닌지 확인
        assertNotNull(study.getOpenedDateTime());

        // memberService의 notify(study)가 호출됐는지 확인
        then(memberService).should().notify(study);
    }
}
