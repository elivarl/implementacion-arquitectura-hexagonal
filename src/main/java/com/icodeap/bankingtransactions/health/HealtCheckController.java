package com.icodeap.bankingtransactions.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealtCheckController {

    @GetMapping
    public String health(){
        return "OK desde el controlador Healt";
    }
}
