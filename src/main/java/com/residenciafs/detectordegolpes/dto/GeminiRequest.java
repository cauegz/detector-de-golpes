package com.residenciafs.detectordegolpes.dto;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.http.codec.multipart.Part;

import java.util.List;

public record GeminiRequest(List<WebProperties.Resources.Chain.Strategy.Content> contents) {
    public record Content(List<Part> parts){}
    public record Part(String text){}
}

