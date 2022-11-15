package com.healthconnect.user.model.util;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
	
	private int status;
	private String message;
	private T result;

	//No Use allowed
	ApiResponse() {

    }

	public ApiResponse(HttpStatus status, String message, T result){
	    this.status = status.value();
	    this.message = message;
	    this.result = result; //== null ? null : AesUtil.encrypt(new Gson().toJson(result));
    }

    public ApiResponse(int status, String message){
        this.status = status;
        this.message = message;
    }

    public ApiResponse(HttpStatus status, String message){
        this.status = status.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
	public String toString() {
		return "ApiResponse [statusCode=" + status + ", message=" + message +"]";
	}


}

