package com.example.vocab_api.application.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.vocab_api.application.dto.LoginRequest;
import com.example.vocab_api.application.dto.RegisterRequest;
import com.example.vocab_api.domain.model.Member;
import com.example.vocab_api.domain.service.MemberDomainService;
import com.example.vocab_api.web.dto.LoginResponse;

class MemberServiceTest {
    @Mock
    private MemberDomainService memberDomainService;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_success() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("user1");
        request.setPassword("pass");
        when(memberDomainService.isUsernameTaken("user1")).thenReturn(false);
        when(memberDomainService.register(anyString(), anyString())).thenReturn(new Member());
        boolean result = memberService.register(request);
        assertTrue(result);
        verify(memberDomainService).register("user1", "pass");
    }

    @Test
    void register_usernameTaken() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("user1");
        request.setPassword("pass");
        when(memberDomainService.isUsernameTaken("user1")).thenReturn(true);
        boolean result = memberService.register(request);
        assertFalse(result);
        verify(memberDomainService, never()).register(anyString(), anyString());
    }

    @Test
    void loginAndGenerateJwt_success() {
        LoginRequest request = new LoginRequest();
        request.setUsername("user1");
        request.setPassword("pass");
        Member member = new Member();
        member.setUsername("user1");
        when(memberDomainService.authenticate("user1", "pass")).thenReturn(Optional.of(member));
        when(memberDomainService.generateJwtForMember(member)).thenReturn("token123");
        Optional<LoginResponse> result = memberService.loginAndGenerateJwt(request);
        assertTrue(result.isPresent());
        assertEquals("token123", result.get().getToken());
        assertEquals("user1", result.get().getUsername());
    }

    @Test
    void loginAndGenerateJwt_fail() {
        LoginRequest request = new LoginRequest();
        request.setUsername("user1");
        request.setPassword("wrong");
        when(memberDomainService.authenticate("user1", "wrong")).thenReturn(Optional.empty());
        Optional<LoginResponse> result = memberService.loginAndGenerateJwt(request);
        assertFalse(result.isPresent());
        verify(memberDomainService, never()).generateJwtForMember(any());
    }
}
