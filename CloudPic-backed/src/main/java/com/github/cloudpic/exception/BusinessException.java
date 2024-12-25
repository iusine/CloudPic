package com.github.cloudpic.exception;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
    public BusinessException(StateCode stateCode) {
        super(stateCode.getMessage());
        this.code = stateCode.getCode();
    }
    public BusinessException(StateCode errorCode,String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
