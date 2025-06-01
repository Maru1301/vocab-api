package com.example.vocab_api.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vocab_api.domain.model.Member;
import com.example.vocab_api.domain.repository.MemberRepository;
import com.example.vocab_api.infrastructure.JwtUtil;

@Service

public class MemberDomainService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public MemberDomainService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    /**
     * Generate JWT token for a member (DDD: domain logic for token issuance)
     */
    public String generateJwtForMember(Member member) {
        return jwtUtil.generateToken(member.getUsername());
    }

    public boolean isUsernameTaken(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }

    public Member register(String username, String password) {
        Member member = new Member();
        member.setUsername(username);
        member.setPasswordHash(passwordEncoder.encode(password));
        member.setRoles("USER");
        return memberRepository.save(member);
    }

    public Optional<Member> authenticate(String username, String password) {
        Optional<Member> memberOpt = memberRepository.findByUsername(username);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            if (passwordEncoder.matches(password, member.getPasswordHash())) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }
}
