package com.residenciafs.detectordegolpes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//define que o retorno do metodo com a rota vai ir pro corpo da página
@RestController
@RequestMapping("/analisar")
public class DetectorController {
    //define a rota
    @GetMapping("/")
    public String helloWorld(){
        return "<h1>hello world</h1>";
    }


}
