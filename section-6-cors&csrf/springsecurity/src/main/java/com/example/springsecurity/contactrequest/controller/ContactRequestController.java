package com.example.springsecurity.contactrequest.controller;


import com.example.springsecurity.contactrequest.dto.ContactRequestDTO;
import com.example.springsecurity.contactrequest.entity.ContactRequest;
import com.example.springsecurity.contactrequest.service.ContactRequestService;
import com.example.springsecurity.exception.ErrorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactRequestController {

    @Autowired
    ContactRequestService contactRequestService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveContactRequest(@Valid @RequestBody ContactRequestDTO contactRequestDTO){
        try {
            ContactRequest contactRequest = contactRequestService.saveContactRequest(contactRequestDTO);
            return ResponseEntity.ok(contactRequest);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage()));
        }
    }
}
