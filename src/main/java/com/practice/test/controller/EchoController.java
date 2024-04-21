package com.practice.test.controller;

import com.practice.test.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
    @Value("${hello.message}")
    private String helloMessage;

    @GetMapping("/hello")
    public Response hello() {
        return new Response(helloMessage);
    }
}
