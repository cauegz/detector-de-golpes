package com.residenciafs.detectordegolpes.controller;

import com.residenciafs.detectordegolpes.dto.MensagemRequest;
import com.residenciafs.detectordegolpes.dto.MensagemResponse;
import com.residenciafs.detectordegolpes.service.AnaliseGolpeService;
import com.residenciafs.detectordegolpes.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//define que o retorno do metodo com a rota vai ir pro corpo da página
@RestController
@RequestMapping("/api/ai")
public class DetectorController {
    private final GeminiService gemini;
    private final AnaliseGolpeService analise;

    @Autowired
    public DetectorController(GeminiService gemini, AnaliseGolpeService analise) {
        this.gemini = gemini;
        this.analise = analise;
    }

    @PostMapping
    public MensagemResponse analisarRequest (@RequestBody MensagemRequest request){
        MensagemResponse response = gemini.analisarMensagemBase(request);
        double porcentagem = analise.calcularPorcentagem(
                response.incompatibilidadeContexto(),
                response.riscoRemetente(),
                response.meioComunicacaoOficial(),
                response.riscoPadraoGolpe()
        );
        String mensagemPorcentagem = analise.classificacaoRisco(porcentagem);
        return new MensagemResponse(
                response.incompatibilidadeContexto(),
                response.riscoRemetente(),
                response.meioComunicacaoOficial(),
                response.riscoPadraoGolpe(),
                response.mensagem(),
                mensagemPorcentagem
        );
    }
}
