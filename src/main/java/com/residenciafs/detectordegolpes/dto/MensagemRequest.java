package com.residenciafs.detectordegolpes.dto;

public record MensagemRequest(String mensagem, String contexto, String empresa, String meioComunicacao) {
}
