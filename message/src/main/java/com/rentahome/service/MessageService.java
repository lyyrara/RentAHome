package com.rentahome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentahome.repository.MessageRepository;

@Service
public class MessageService{

    @Autowired
    MessageRepository messageRepository;

    // public 
}
