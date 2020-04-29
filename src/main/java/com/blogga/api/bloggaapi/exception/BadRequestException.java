package com.blogga.api.bloggaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@NoArgsConstructor
public class BadRequestException extends Exception {
    private static final long serialVersionUID = -3435609094182340326L;

    public BadRequestException(String error) {
        super(error);
    }
}