package com.example.exame_backend.controller;



import com.example.exame_backend.service.email.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {


    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }


    @GetMapping("/send")
    public void sendEmail(@RequestParam(value = "to") String to,
                                    @RequestParam(value = "email") String email) {


         emailSenderService.send(to, email);


    }


}
