package com.residenciafs.detectordegolpes.dto;

import java.util.List;

public record GeminiResponse(List<Candidate> candidates) {
    public record Candidate(Content content){}
    public record Content(List<GeminiRequest.Part> parts){}
    public record Part(String text){}

    public String extrairTexto(){
        if(candidates==null || candidates.isEmpty())return "";
        return candidates.get(0).content().parts().get(0).text();
    }
    //to terminando(vou dar commit pra juntar rapidinho!)
}
