package com.blogga.api.bloggaapi.response;

import java.util.ArrayList;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HttpResponseMeta {

    private String status;
    private int code;
    private String message;
    private ArrayList<String> warnings;

}