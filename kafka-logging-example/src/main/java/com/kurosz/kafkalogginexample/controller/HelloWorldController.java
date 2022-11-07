package com.kurosz.kafkalogginexample.controller;

import com.kurosz.logging.annotations.LogProcessedEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping
    @LogProcessedEvent
    public String sayHello(){
        return "Hello";
    }
}
