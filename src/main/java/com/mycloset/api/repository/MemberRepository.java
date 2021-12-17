package com.mycloset.api.repository;

import com.mycloset.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    @Override
    List<Member> findAll();
}
