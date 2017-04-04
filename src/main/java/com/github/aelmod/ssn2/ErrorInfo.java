package com.github.aelmod.ssn2;

import lombok.Getter;

@Getter
public class ErrorInfo {

    private String url;

    private String message;

    public ErrorInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }
}
