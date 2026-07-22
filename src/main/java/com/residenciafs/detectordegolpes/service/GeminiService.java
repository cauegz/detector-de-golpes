package com.residenciafs.detectordegolpes.service;

import com.residenciafs.detectordegolpes.dto.GeminiRequest;
import com.residenciafs.detectordegolpes.dto.GeminiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GeminiService {
    private final RestClient restClient;
    private final String apiUrl;
    private final String apiKey;


    public GeminiService(@Value("${gemini.api.url}") String apiUrl,
                         @Value("${gemini.api.key}") String apiKey){
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.restClient = RestClient.create(); //´ra instanciar o cliente http do spring
    }

    public String analisarMensagemBase(String mensagem){
        var body = new GeminiRequest(List.of(
                new GeminiRequest.Content(List.of(
                    new GeminiRequest.Part(mensagem)
                ))
        ));

        try{
            GeminiResponse resposta = restClient.post()
                    .uri(apiUrl + "?key="+ apiKey)
                    .header("Content-Type", "application/json")
                    .body(body)
                    .retrieve()
                    .body(GeminiResponse.class); //json -> record

            //retorna e extrai a resposta
            return resposta != null ? resposta.extrairTexto() : "Erro, calhou de não receber, o gemini não gosta de vc";
        }catch(Exception e){
            System.err.println(e.getMessage());
            return "Erro";
        }
    }
}
