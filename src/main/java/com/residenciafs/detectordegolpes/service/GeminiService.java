package com.residenciafs.detectordegolpes.service;

import com.residenciafs.detectordegolpes.dto.GeminiRequest;
import com.residenciafs.detectordegolpes.dto.GeminiResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
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

    public String analisarMensagemBase(String mensagem){
        PromptTemplate promptTemplate =
                new PromptTemplate(resource);

        String promptCompleto = promptTemplate.render(
                Map.of("mensagem", mensagem)
        );

        return chatClient
                .prompt()
                .user(promptCompleto)
                .call()
                .content();
        }
    }

