package com.nttdata.microservice.client.common;

import lombok.Data;

@Data
public class FormResponse {
    private boolean success;
    private String message;
    private Object data;
    private String error;

    public FormResponse() {
        success = true;
    }
}
