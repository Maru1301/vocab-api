package com.example.vocab_api.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vocab_api.application.dto.LoginRequest;
import com.example.vocab_api.application.dto.RegisterRequest;
import com.example.vocab_api.domain.model.Member;
import com.example.vocab_api.domain.service.MemberDomainService;
import com.example.vocab_api.web.dto.LoginResponse;

@Service
public class MemberService {
    private final MemberDomainService memberDomainService;

    @Autowired
    public MemberService(MemberDomainService memberDomainService) {
        this.memberDomainService = memberDomainService;
    }

    public boolean register(RegisterRequest request) {
        if (memberDomainService.isUsernameTaken(request.getUsername())) {
            return false;
        }
        memberDomainService.register(request.getUsername(), request.getPassword());
        return true;
    }

    public Optional<LoginResponse> loginAndGenerateJwt(LoginRequest request) {
        Optional<Member> memberOpt = memberDomainService.authenticate(request.getUsername(), request.getPassword());
        if (memberOpt.isPresent()) {
            String token = memberDomainService.generateJwtForMember(memberOpt.get());
            return Optional.of(new LoginResponse(token, memberOpt.get().getUsername()));
        } else {
            return Optional.empty();
        }
    }
}
