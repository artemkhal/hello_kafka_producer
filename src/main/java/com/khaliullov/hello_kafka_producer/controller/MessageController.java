package com.khaliullov.hello_kafka_producer.controller;

import com.khaliullov.hello_kafka_producer.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringReader;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MessageController {

    private final SenderService service;

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String key,
                                              @RequestParam String message){
        service.send(key, message);
        return ResponseEntity.ok("key = " + key + "\n" + "message = " + message);
    }

    @GetMapping("/sendWP")
    public ResponseEntity<String> sendMessageWithoutPartition(@RequestParam String key,
                                              @RequestParam String message){
        service.sendWithoutPart(key, message);
        return ResponseEntity.ok("key = " + key + "\n" + "message = " + message);
    }
}
