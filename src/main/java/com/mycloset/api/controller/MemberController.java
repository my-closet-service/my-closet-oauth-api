package com.mycloset.api.controller;

import com.mycloset.api.entity.Member;
import com.mycloset.api.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/v1")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(value="/users")
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
