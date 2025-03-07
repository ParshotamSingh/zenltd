package com.zenltd.controller;

import com.zenltd.dto.MemberDto;
import com.zenltd.repositories.MemberRepository;
import com.zenltd.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;
    @Operation(summary = "Save Member")
    @PostMapping(value = "/saveMember")
    public void saveMember(@Valid @RequestBody MemberDto memberDto){

        memberService.saveMember(memberDto);
    }
    @Operation(summary = "Get Member By Id")
    @GetMapping(value = "/getMemberById")
    public ResponseEntity<MemberDto> getMemberById(@Valid @RequestParam long id) {
        MemberDto memberDto = memberService.getMemberById(id);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }
}
