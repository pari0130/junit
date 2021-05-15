package com.study.junit.member;

import com.study.junit.domain.Member;
import com.study.junit.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId) throws MemberNotFoundException;

    void validate(Long membereId);

    void notify(Study study);

    void notify(Member member);
}
