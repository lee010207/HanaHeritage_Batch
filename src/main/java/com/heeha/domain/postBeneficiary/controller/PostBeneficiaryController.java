package com.heeha.domain.postBeneficiary.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Example", description = "이해를 돕기 위한 예시 api 입니다.")
@RequestMapping("/example")
public class PostBeneficiaryController {

    /*
    TODO : DELETE ME!
    아래 main함수는 스웨거 표현을 위한 어노테이션 확인을 위해 추가한 임시 컨트롤러입니다. 확인 후 지워주세요!
     */
    @Operation(summary = "입력한 숫자 확인", description = "Long no 입력 후 결과 문자열 리턴 ")
    @Parameter(name = "no", description = "no 입력")
    @GetMapping(value = "/{no}")
    public String main(@RequestParam("no") Long no) {
        return "입력한 숫자는 " + no + "입니다.";
    }
}