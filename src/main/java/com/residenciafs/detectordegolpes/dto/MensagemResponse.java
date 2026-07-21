package com.residenciafs.detectordegolpes.dto;

public record MensagemResponse(boolean golpe, String mensagem, Integer percentualGolpe) {
}
