package com.residenciafs.detectordegolpes.service;

import com.residenciafs.detectordegolpes.dto.MensagemRequest;
import com.residenciafs.detectordegolpes.dto.MensagemResponse;
import com.residenciafs.detectordegolpes.exception.APIKeyInvalid;
import com.residenciafs.detectordegolpes.exception.GeminiException;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public class GeminiService {
    private static final Logger log = LoggerFactory.getLogger(GeminiService.class);
    private final ChatClient chatClient;
    private final Resource resource;


    public GeminiService(ChatClient.Builder chatClientBuilder,
                         @Value("classpath:prompts/base.txt")
                         Resource promptResource,

                         @Value("${spring.ai.google.genai.api-key:}")
                         String apiKey){
        this.chatClient = chatClientBuilder.build();
        this.resource = promptResource;
        if(apiKey == null || apiKey.isBlank()){
            throw new APIKeyInvalid("GEMINI_API_KEY deve ser definida em um arquivo .env");
        }
    }

    public MensagemResponse analisarMensagemBase(MensagemRequest mensagem) {
        try {
            PromptTemplate promptTemplate =
                    new PromptTemplate(resource);

            String promptCompleto = promptTemplate.render(
                    Map.of(
                            "mensagem", mensagem.mensagem(),
                            "contexto", mensagem.contexto(),
                            "empresa", mensagem.empresa(),
                            "meioComunicacao", mensagem.meioComunicacao()
                    )
            );
            return chatClient
                    .prompt()
                    .user(promptCompleto)
                    .call() //sends a request to the AI model
                    .entity(MensagemResponse.class); //returns the AI model's response as a entity
        } catch (Exception e){
            log.error("Falha ao chamar o Gemini", e);
            throw new GeminiException("Erro de comunicação com o gemini: " + e.getMessage(), e);
        }
    }
}

