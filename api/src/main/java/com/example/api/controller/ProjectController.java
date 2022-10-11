package com.example.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @GetMapping
    public String hello() {
        return "Hello world";
    }

    @DeleteMapping
    public String helloPost() {
        System.out.println("hello");
        return "Hello world";
    }
}
