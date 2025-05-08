package com.nttdata.microservice.account.common;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String error;

    public ApiResponse(boolean success, String message,T data, String error) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "success", data, null);
    }

    public static <T> ApiResponse<T> failure(String error) {
        return new ApiResponse<>(false,"failure", null, error);
    }
}
