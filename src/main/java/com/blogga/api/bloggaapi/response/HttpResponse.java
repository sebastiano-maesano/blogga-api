package com.blogga.api.bloggaapi.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HttpResponse<T> {

    private String status;
    private int code;
    private String message;
    private T data;

}