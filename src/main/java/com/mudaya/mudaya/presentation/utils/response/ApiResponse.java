package com.mudaya.mudaya.presentation.utils.response;

public class ApiResponse<T> {
    private boolean status;
    private String message;
    private T response;

    public ApiResponse(boolean status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public ApiResponse(boolean status, String message) {
        this(status, message, null);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message);
    }

    // Getters y Setters
    public boolean isStatus() { return status; }
    public String getMessage() { return message; }
    public T getResponse() { return response; }

    public void setStatus(boolean status) { this.status = status; }
    public void setMessage(String message) { this.message = message; }
    public void setResponse(T response) { this.response = response; }
}

