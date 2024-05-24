package com.heeha.domain.postBeneficiary.controller;


import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.heeha.global.config.BaseResponseStatus.REQUEST_ERROR;

@RestController
@Tag(name = "Example", description = "이해를 돕기 위한 예시 api 입니다.")
@RequestMapping("/example")
public class PostBeneficiaryController {

    /*
    TODO : DELETE ME!
    아래 main, main2 함수는 스웨거 표현을 위한 어노테이션 확인을 위해 추가한 임시 컨트롤러입니다. 확인 후 지워주세요!
     */
    @Operation(summary = "입력한 숫자 확인", description = "Long no 입력 후 결과 문자열 리턴 ")
    @Parameter(name = "no", description = "no 입력")
    @GetMapping(value = "/{no}")
    public BaseResponse<String> main(@RequestParam("no") Long no) {
        return new BaseResponse<>("입력한 숫자는 " + no + "입니다.");
    }

    @Operation(summary = "exception 발생", description = "exception 리턴 ")
    @GetMapping(value = "/exception")
    public BaseResponse<String> main2() {
        try{
            throw new BaseException(REQUEST_ERROR);
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}
