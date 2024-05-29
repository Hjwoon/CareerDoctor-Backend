package com.homepage.careerdoctor.user.service;

import com.homepage.careerdoctor.user.dto.UserSigninDto;
import com.homepage.careerdoctor.user.dto.UserSignupDto;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<CustomApiResponse<?>> signup(UserSignupDto dto); // 회원가입
    public ResponseEntity<CustomApiResponse<?>> signin(UserSigninDto dto); // 로그인
}
