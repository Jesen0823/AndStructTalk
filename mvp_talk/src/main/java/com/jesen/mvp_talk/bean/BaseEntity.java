package com.jesen.mvp_talk.bean;

public class BaseEntity {

    private int code;
    private boolean success;
    private String error;

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError(String error) {
        this.error = error;
    }
}
