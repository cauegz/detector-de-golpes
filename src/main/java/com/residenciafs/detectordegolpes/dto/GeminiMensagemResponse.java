package com.residenciafs.detectordegolpes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeminiMensagemResponse(
        @JsonProperty("incompatibilidade_contexto")
        int incompatibilidadeContexto,

        @JsonProperty("risco_remetente")
        int riscoRemetente,

        @JsonProperty("meio_comunicacao_oficial")
        int meioComunicacaoOficial,

        @JsonProperty("risco_padrao_golpe")
        int riscoPadraoGolpe,

        String mensagem
) {}
