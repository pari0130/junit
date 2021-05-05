package com.study.junit.member;

import com.study.junit.domain.Member;
import com.study.junit.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
