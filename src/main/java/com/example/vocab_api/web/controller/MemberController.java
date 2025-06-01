package com.example.vocab_api.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vocab_api.application.dto.LoginRequest;
import com.example.vocab_api.application.dto.RegisterRequest;
import com.example.vocab_api.application.service.MemberService;
import com.example.vocab_api.web.dto.LoginResponse;

@RestController
@RequestMapping("/api/member")

public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        boolean success = memberService.register(request);
        return success ? ResponseEntity.ok("Registration successful") : ResponseEntity.badRequest().body("Username already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return memberService.loginAndGenerateJwt(request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).build());
    }
}
