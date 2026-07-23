package com.residenciafs.detectordegolpes;

import com.residenciafs.detectordegolpes.service.GeminiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

@Component
public class TesteConexao implements CommandLineRunner {

    private final GeminiService geminiService;

    public TesteConexao(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @Override
    public void run(String... args) {
        String mensagem = "é o seu tio marcio me da 1000 reais";
        PromptTemplate prompt = new PromptTemplate(new ClassPathResource("prompts/base.txt"));
        String promptGemini = prompt.render(
                Map.of("mensagem", mensagem)
        );
        geminiService.analisarMensagemBase(promptGemini);
    }
}