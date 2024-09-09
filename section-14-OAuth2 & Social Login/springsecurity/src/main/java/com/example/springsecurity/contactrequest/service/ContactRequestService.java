package com.example.springsecurity.contactrequest.service;

import com.example.springsecurity.contactrequest.dto.ContactRequestDTO;
import com.example.springsecurity.contactrequest.entity.ContactRequest;
import com.example.springsecurity.contactrequest.repository.ContactRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactRequestService {

    @Autowired
    ContactRequestRepository contactRequestRepository;

    public ContactRequest saveContactRequest(ContactRequestDTO contactRequestDTO){
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setName(contactRequestDTO.getName());
        contactRequest.setEmail(contactRequestDTO.getEmail());
        contactRequest.setSubject(contactRequestDTO.getSubject());
        contactRequest.setMessage(contactRequestDTO.getMessage());
        return contactRequestRepository.save(contactRequest);
    }
}
