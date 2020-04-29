package com.blogga.api.bloggaapi.service;

import com.blogga.api.bloggaapi.response.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public class HttpResponderService<T> {

    public HttpResponse<T> ok(T data) {
        HttpResponse<T> response = new HttpResponse<T>();

        response.setCode(200);
        response.setMessage("success");
        response.setStatus("OK");
        response.setData(data);

        return response;
    }
}