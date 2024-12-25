package com.github.cloudpic.common;

import com.github.cloudpic.exception.StateCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author iusie
 * @description
 * @date 2024/12/25
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data){
        this(code,data,"");
    }

    public BaseResponse(StateCode stateCode)
    {
        this(stateCode.getCode(),null,stateCode.getMessage());
    }
}