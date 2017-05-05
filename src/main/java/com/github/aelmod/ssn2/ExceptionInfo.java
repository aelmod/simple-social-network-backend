package com.github.aelmod.ssn2;

import lombok.Getter;

@Getter
public class ExceptionInfo {

    private final String url;

    private final String message;

    public ExceptionInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }
}
