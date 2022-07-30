package com.didow.didowapi.adapters.responses;

public class BaseResponse {
  private String status = "success";
  private String message;
  private int statusCode;

  public BaseResponse() {
    this.message = "success";
    this.statusCode = 200;
  }

  public BaseResponse(String message) {
    this.message = message;
    this.statusCode = 200;
  }

  public BaseResponse(String message, int statusCode) {
    this.message = message;
    this.statusCode = statusCode;
  }

  public String getStatus() {
    return this.status;
  }

  public String getMessage() {
    return this.message;
  }

  public int getStatusCode() {
    return this.statusCode;
  }
}
