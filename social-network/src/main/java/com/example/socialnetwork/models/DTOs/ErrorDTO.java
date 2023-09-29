package com.example.socialnetwork.models.DTOs;

import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorDTO {
    private String endpoint;
    private String message;
    private String time;

    public ErrorDTO (String endpoint, String message){
        this.endpoint = endpoint;
        this.message = message;
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.time = LocalDateTime.now().format(myFormatObj);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
