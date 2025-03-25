package com.younghun.library.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_PASSWORD("ACCOUNT-001","PASSWORD DOESN'T MATCH"),
    ALREADY_EXIST_USERNAME("ACCOUNT-002", "USERNAME ALREADY EXISTED");

    private final String code;
    private final String message;


}