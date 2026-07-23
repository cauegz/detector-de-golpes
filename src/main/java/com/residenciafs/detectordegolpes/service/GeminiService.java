package com.residenciafs.detectordegolpes.service;

import com.residenciafs.detectordegolpes.dto.MensagemRequest;
import com.residenciafs.detectordegolpes.dto.MensagemResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GeminiService {
    private final ChatClient chatClient;
    private final Resource resource;


    public GeminiService(ChatClient.Builder chatClientBuilder,
                         @Value("classpath:prompts/base.txt")
                         Resource promptResource){
        this.chatClient = chatClientBuilder.build();
        this.resource = promptResource;
    }

    public MensagemResponse analisarMensagemBase(MensagemRequest mensagem){
        PromptTemplate promptTemplate =
                new PromptTemplate(resource);

        String promptCompleto = promptTemplate.render(
                Map.of("mensagem", mensagem)
        );
        return chatClient
                .prompt()
                .user(promptCompleto)
                .call()
                .entity(MensagemResponse.class);
        }
    }

