package com.example.vocab_api.domain.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.vocab_api.domain.model.Member;
import com.example.vocab_api.domain.repository.MemberRepository;
import com.example.vocab_api.infrastructure.JwtUtil;

class MemberDomainServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private MemberDomainService memberDomainService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isUsernameTaken_true() {
        when(memberRepository.findByUsername("user1")).thenReturn(Optional.of(new Member()));
        assertTrue(memberDomainService.isUsernameTaken("user1"));
    }

    @Test
    void isUsernameTaken_false() {
        when(memberRepository.findByUsername("user1")).thenReturn(Optional.empty());
        assertFalse(memberDomainService.isUsernameTaken("user1"));
    }

    @Test
    void register_success() {
        when(passwordEncoder.encode("pass")).thenReturn("hashed");
        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Member member = memberDomainService.register("user1", "pass");
        assertEquals("user1", member.getUsername());
        assertEquals("hashed", member.getPasswordHash());
        assertEquals("USER", member.getRoles());
    }

    @Test
    void authenticate_success() {
        Member member = new Member();
        member.setUsername("user1");
        member.setPasswordHash("hashed");
        when(memberRepository.findByUsername("user1")).thenReturn(Optional.of(member));
        when(passwordEncoder.matches("pass", "hashed")).thenReturn(true);
        Optional<Member> result = memberDomainService.authenticate("user1", "pass");
        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getUsername());
    }

    @Test
    void authenticate_fail_wrong_password() {
        Member member = new Member();
        member.setUsername("user1");
        member.setPasswordHash("hashed");
        when(memberRepository.findByUsername("user1")).thenReturn(Optional.of(member));
        when(passwordEncoder.matches("wrong", "hashed")).thenReturn(false);
        Optional<Member> result = memberDomainService.authenticate("user1", "wrong");
        assertFalse(result.isPresent());
    }

    @Test
    void authenticate_fail_no_user() {
        when(memberRepository.findByUsername("nouser")).thenReturn(Optional.empty());
        Optional<Member> result = memberDomainService.authenticate("nouser", "pass");
        assertFalse(result.isPresent());
    }

    @Test
    void generateJwtForMember_delegatesToJwtUtil() {
        Member member = new Member();
        member.setUsername("user1");
        when(jwtUtil.generateToken("user1")).thenReturn("jwt-token");
        String token = memberDomainService.generateJwtForMember(member);
        assertEquals("jwt-token", token);
    }
}
