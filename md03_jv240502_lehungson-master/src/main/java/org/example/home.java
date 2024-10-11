package org.example;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class home {

    @GetMapping("")
    public String loadTestPage() {
        return "home.html";
    }
}