package com.blogga.api.bloggaapi.service;

import java.util.ArrayList;

import com.blogga.api.bloggaapi.response.HttpResponse;
import com.blogga.api.bloggaapi.response.HttpResponseMeta;

import org.springframework.stereotype.Service;

@Service
public class HttpResponderService<T> {

    public HttpResponse<T> ok(T data) {
        HttpResponse<T> response = new HttpResponse<T>();
        HttpResponseMeta meta = new HttpResponseMeta();

        meta.setCode(200);
        meta.setMessage("success");
        meta.setStatus("OK");
        meta.setWarnings(new ArrayList<>());

        response.setData(data);
        response.setMeta(meta);

        return response;
    }
}