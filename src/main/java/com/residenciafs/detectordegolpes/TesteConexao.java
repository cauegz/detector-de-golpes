package com.residenciafs.detectordegolpes;

import com.residenciafs.detectordegolpes.service.GeminiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TesteConexao implements CommandLineRunner {

    private final GeminiService geminiService;

    public TesteConexao(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @Override
    public void run(String... args) {
        System.out.println("=================================================");
        System.out.println("ENVIANDO MENSAGEM DE TESTE PARA A IA...");

        String mensagemSuspeita = "Diga 'Conectado!' se você está me ouvindo";

        String resposta = geminiService.analisarMensagemBase(mensagemSuspeita);

        System.out.println("RESPOSTA: "+resposta);
    }
}