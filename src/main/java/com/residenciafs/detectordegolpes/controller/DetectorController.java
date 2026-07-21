package com.residenciafs.detectordegolpes.controller;

import com.residenciafs.detectordegolpes.dto.MensagemRequest;
import com.residenciafs.detectordegolpes.dto.MensagemResponse;
import org.springframework.web.bind.annotation.*;

//define que o retorno do metodo com a rota vai ir pro corpo da página
@RestController
@RequestMapping("/analisar")
public class DetectorController {
    //define a rota
    @GetMapping("/")
    public String helloWorld(){
        return "<h1>hello world</h1>";
    }

    @PostMapping
    public MensagemResponse analisarRequest (@RequestBody MensagemRequest request){
        return new MensagemResponse(true, "é um golpe", 100);
    }
}
