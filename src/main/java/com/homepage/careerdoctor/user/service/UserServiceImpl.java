package com.homepage.careerdoctor.user.service;

import com.homepage.careerdoctor.domain.User;
import com.homepage.careerdoctor.user.dto.UserSigninDto;
import com.homepage.careerdoctor.user.dto.UserSignupDto;
import com.homepage.careerdoctor.user.repository.UserRepository;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    // 회원가입
    @Override
    public ResponseEntity<CustomApiResponse<?>> signup(UserSignupDto userSignupDto) {

        Optional<User> foundUser = userRepository.findByUserId(userSignupDto.getUserId());

        // 아이디 중복인 경우
        if (foundUser.isPresent()) {
            return ResponseEntity.status(400)
                    .body(CustomApiResponse.createFailWithoutData(400, "중복된 아이디가 존재합니다."));
        }

        User newuser = User.builder()
                .userId(userSignupDto.getUserId())
                .password(userSignupDto.getPassword())
                .build();

        userRepository.save(newuser);

        return ResponseEntity.status(201)
                .body(CustomApiResponse.createSuccess(201, null, "회원가입에 성공하였습니다."));
    }

    // 로그인
    @Override
    public ResponseEntity<CustomApiResponse<?>> signin(UserSigninDto userSigninDto) {
        // 회원이 DB에 존재하는지 확인
        Optional<User> foundUser = userRepository.findByUserId(userSigninDto.getUserId());

        // DB에 회원이 존재하지 않는다면
        if(foundUser.isEmpty()) {
            return userNotFound();
        }

        // DB에 저장된 비밀번호와 사용자가 로그인에 요청한 비밀번호가 일치하는지 확인
        // 일치하지 않는다면
        if(!userSigninDto.getPassword().equals(foundUser.get().getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.UNAUTHORIZED.value(),
                            "비밀번호가 일치하지 않습니다."));
        }

        // 모든 확인 절차를 통과하였다면 로그인 허용
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(), foundUser.get().getUserId(),
                        "로그인을 성공하였습니다."));
    }

    // 일치하는 회원이 없을 경우 404 에러 반환
    private ResponseEntity<CustomApiResponse<?>> userNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(),
                        "존재하지 않는 회원입니다."));
    }
}
