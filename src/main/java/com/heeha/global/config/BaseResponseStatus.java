package com.heeha.global.config;

import lombok.Getter;

/**
 * [주의] 응답 코드 관리
 * 작성 요령 : 3000번대 이후부터는 코드가 겹칠 수 있으니, 아래 본인에게 맞는 영역만 사용할 것!!
 * 100~199 : 변정흠
 * 200~299 : 유민아
 * 300~399 : 이지후
 * 400~499 : 정찬수
 * 500~599 : 황유진
 * 600~699 : 황혜림
 * 예) 변정흠이 deathNotifier 관련 에러를 추가하고 싶다면? 6100 ~ 6199 내의 코드만 사용
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    SYSTEM_ERROR(false,2004, "알 수 없는 오류 서버팀에 문의주세요."),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    // account (3000 ~ 3999)
    FAIL_TRANSFER(false, 3300, "계좌이체 실패"),
    NO_FROM_ACCOUNT(false, 3301, "보내는 계좌가 없습니다"),
    NO_TO_ACCOUNT(false, 3302, "받는 계좌가 없습니다"),
    INVALID_BALANCE(false, 3303, "계좌 잔액이 부족합니다."),
    WRONG_PASSWORD(false, 3304, "계좌 비밀번호가 다릅니다."),
    FAIL_REGISTER_AUTOTRANSFER(false, 3500, "자동이체 등록 실패"),

    // autoTransfer (4000 ~ 4999)

    // customer (5000 ~ 5999)
    DUPLICATE_CUSTOMER(false, 5000, "동일한 휴대폰 또는 주민번호로 가입된 고객입니다.");

    // deathNotifier (6000 ~ 6999)

    // depositProduct (7000 ~ 7999)

    // history (8000 ~ 8999)

    // livingTrust (9000 ~ 9999)

    // postBeneficiary (10000 ~ 10999)

    // property (11000 ~ 11999)

    // savingProduct (12000 ~ 12999)

    // signDeposit (13000 ~ 13999)

    // signSaving (14000 ~ 14999)


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}