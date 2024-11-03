package com.upskillers.upskillers.exceptions;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputValidationObject {
    private String message;
    private Map<String, String> errors;    
}
