package com.hua.webflux.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * company: www.kaikeba.com
 * Author: Rey
 */
@Getter
@Setter
@NoArgsConstructor
public class StudentException extends RuntimeException {
    private String errField;
    private String errValue;

    public StudentException(String message, String errField, String errValue) {
        super(message);
        this.errField = errField;
        this.errValue = errValue;
    }
}
