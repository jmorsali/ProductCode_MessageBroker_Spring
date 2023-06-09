package com.blueboders.productcodebroker.controller;

import com.blueboders.productcodebroker.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/message")
public class messageController {

    private final MessageService messageService;
       @Autowired
    public messageController(MessageService messageService) {
        this.messageService = messageService;

    }

    @PutMapping("pushfile/q1")
    public ResponseEntity<String> PushFile_Q1(@RequestPart("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {

            try {
                byte[] bytes = file.getBytes();
                String csvData = new String(bytes);
                messageService.Push_Q1(csvData);
                return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("UnHandled error during file process ::>" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

            }
        } else {
            return new ResponseEntity<>("No file uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("pushfile/q2")
    public ResponseEntity<String> PushFile_Q2(@RequestPart("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {

            try {
                byte[] bytes = file.getBytes();
                messageService.Push_Q2(bytes);
                return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("UnHandled error during file process ::>" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

            }
        } else {
            return new ResponseEntity<>("No file uploaded", HttpStatus.BAD_REQUEST);
        }
    }

}
