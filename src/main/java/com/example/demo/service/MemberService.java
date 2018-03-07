package com.example.demo.service;

import com.example.demo.domain.Member;

import java.util.List;

public interface MemberService {

    public List<Member> getMembers();

    public Member getMember(int tid);
}
