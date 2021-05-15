package com.study.junit.study;

import com.study.junit.domain.Member;
import com.study.junit.domain.Study;
import com.study.junit.member.MemberNotFoundException;
import com.study.junit.member.MemberService;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;
    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) throws MemberNotFoundException {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("맴버 가 없습니다. '" + memberId + "'")));
        Study newStudy = repository.save(study);
        memberService.notify(newStudy);
        memberService.notify(member.get());
        return repository.save(study);
    }
}
