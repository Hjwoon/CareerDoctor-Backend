package com.homepage.careerdoctor.user.controller;

import com.homepage.careerdoctor.user.dto.UserSigninDto;
import com.homepage.careerdoctor.user.dto.UserSignupDto;
import com.homepage.careerdoctor.user.service.UserServiceImpl;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/careerdoctor")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    // 회원가입
    @PostMapping("/signup")
    private ResponseEntity<CustomApiResponse<?>> signup(@Valid @RequestBody UserSignupDto dto) {
        return userService.signup(dto);
    }

    // 로그인
    @PostMapping("/signin")
    private ResponseEntity<CustomApiResponse<?>> signin(@RequestBody @Valid UserSigninDto dto) {
        return userService.signin(dto);
    }
}
