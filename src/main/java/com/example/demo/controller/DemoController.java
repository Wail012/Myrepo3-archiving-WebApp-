package com.example.demo.controller;


import com.example.demo.model.DocEtud;
import com.example.demo.Service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/")
public class DemoController {


    @Autowired
    private DocService docService;

    @GetMapping
    public String Login( ) {

        return "Login";

    }


    @PostMapping
    public String Loginpost( @RequestParam("id") String id,@RequestParam("password") String pwd,@RequestParam("role") String role,Model model) {

        int trouve;

        trouve= docService.Login( Long.parseLong(id),pwd,Integer.parseInt(role));
        if(trouve==1) {
            if(role.equals("4")) {

                return "WelcomeAdminDba";
            }
        }
        return "Login";
    }
}
