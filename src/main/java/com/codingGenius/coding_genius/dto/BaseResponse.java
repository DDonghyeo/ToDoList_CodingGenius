package com.codingGenius.coding_genius.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.codingGenius.coding_genius.dto.BaseResponseStatus.SUCCESS;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"code", "message"})
public class BaseResponse<T> {

    private final int code;
    private final String message;

    public BaseResponse() {
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
    }

}
