package com.residenciafs.detectordegolpes.controller;

import com.residenciafs.detectordegolpes.dto.MensagemRequest;
import com.residenciafs.detectordegolpes.dto.MensagemResponse;
import com.residenciafs.detectordegolpes.service.GeminiService;
import org.springframework.web.bind.annotation.*;

//define que o retorno do metodo com a rota vai ir pro corpo da página
@RestController
@RequestMapping("/api/ai")
public class DetectorController {
    private final GeminiService gemini;

    public DetectorController(GeminiService gemini) {
        this.gemini = gemini;
    }

    @PostMapping
    public MensagemResponse analisarRequest (@RequestBody MensagemRequest request){
        return gemini.analisarMensagemBase(request);
    }
}
