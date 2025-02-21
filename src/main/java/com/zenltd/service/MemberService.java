package com.zenltd.service;

import com.zenltd.dto.MemberDto;
import com.zenltd.entity.Member;
import com.zenltd.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public void saveMember(MemberDto memberDto) {
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setAddressId(memberDto.getAddressId());
        member.setGstNo(memberDto.getGstNo());
        member.setRepresentativeName(memberDto.getRepresentativeName());
        member.setPhoneNo(memberDto.getPhoneNo());
        member.setEmailId(memberDto.getEmailId());
        member.setAddressProofId(memberDto.getAddressProofId());
        member.setConstitution(memberDto.getConstitution());
        member.setPanNo(memberDto.getPanNo());
        memberRepository.save(member);
    }
    public MemberDto getMemberById(long id) {
        Member member = memberRepository.getMemberById(id);
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setName(member.getName());
        memberDto.setAddressId(member.getAddressId());
        memberDto.setRepresentativeName(member.getRepresentativeName());
        memberDto.setPhoneNo(member.getPhoneNo());
        memberDto.setEmailId(member.getEmailId());
        memberDto.setGstNo(member.getGstNo());
        memberDto.setAddressProofId(member.getAddressProofId());
        memberDto.setConstitution(member.getConstitution());
        memberDto.setPanNo(member.getPanNo());
        return memberDto;
    }
}

