package com.residenciafs.detectordegolpes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MensagemRequest(
        @NotBlank(message = "o campo de mensagem não pode estar vazio")
        String mensagem,
        @NotNull(message = "o campo contexto não foi enviado")
        String contexto,
        @NotNull(message = "o campo empresa não foi enviado")
        String empresa,
        @NotNull(message = "o campo meio de comunicação não foi enviado")
        String meioComunicacao) {
}
