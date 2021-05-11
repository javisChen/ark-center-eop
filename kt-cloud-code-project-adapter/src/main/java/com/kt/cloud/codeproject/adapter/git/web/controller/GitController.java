package com.kt.cloud.codeproject.adapter.git.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/git")
public class GitController {

    @RequestMapping("/callback")
    public String success(String code) {
        return code;
    }
}
