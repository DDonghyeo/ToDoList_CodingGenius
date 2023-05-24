package com.codingGenius.coding_genius.dto;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(200, "로딩 성공"),

    INVALID(204, "일치하지 않음"),

    INVALID_EMAIL(204, "유효하지 않은 이메일"),

    /**
     * 400 : Request 오류
     */
    REQUEST_ERROR(400, "파라미터 오류"),

    //500 서버 오류
    SERVER_ERROR(500, "서버 오류");

    private final int code;
    private final String message;


    private BaseResponseStatus(int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 매핑
        this.code = code;
        this.message = message;
    }
}
