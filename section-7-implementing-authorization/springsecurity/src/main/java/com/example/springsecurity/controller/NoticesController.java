package com.example.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notice")
public class NoticesController {

    @GetMapping("/noticeDetails")
    public String getNoticeDetails(){
        return "My Notice details";
    }
}
