package com.zenltd.controller;

import com.zenltd.dto.MemberDto;
import com.zenltd.repositories.MemberRepository;
import com.zenltd.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;
    @PostMapping(value = "/saveMember")
    public void saveMember(@RequestBody MemberDto memberDto){

        memberService.saveMember(memberDto);
    }
    //*********************************************************
    @GetMapping(value = "/getMemberById")
    public ResponseEntity<MemberDto> getMemberById(@RequestParam long id) {
        MemberDto memberDto = memberService.getMemberById(id);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }
}
