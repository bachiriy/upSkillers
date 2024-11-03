package com.upskillers.upskillers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCustom {
    private int code;
    private String message;

    public ResponseCustom(String message){
        this.message = message;
    }
}
