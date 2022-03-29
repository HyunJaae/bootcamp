package com.sparta.devleeblog.validator;

import com.sparta.devleeblog.dto.SignupRequestDto;
import com.sparta.devleeblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<SignupRequestDto> {

    private final UserRepository userRepository;


    @Override
    protected void doValidate(SignupRequestDto dto, Errors errors) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}
