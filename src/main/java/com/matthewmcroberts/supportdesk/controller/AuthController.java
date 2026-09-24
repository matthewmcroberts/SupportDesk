package com.matthewmcroberts.supportdesk.controller;

import com.matthewmcroberts.supportdesk.dto.response.AuthResponseDto;
import com.matthewmcroberts.supportdesk.dto.request.LoginRequestDto;
import com.matthewmcroberts.supportdesk.dto.request.RegisterUserRequestDto;
import com.matthewmcroberts.supportdesk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody RegisterUserRequestDto requestDto) {
        authService.registerUser(requestDto.getEmail(), requestDto.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        final AuthResponseDto responseDto = authService.login(requestDto.getEmail(), requestDto.getPassword());
        return ResponseEntity.ok().body(responseDto);
    }
}
