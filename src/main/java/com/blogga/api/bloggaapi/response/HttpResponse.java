package com.blogga.api.bloggaapi.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HttpResponse<T> {

    private T data;
    private HttpResponseMeta meta;

}