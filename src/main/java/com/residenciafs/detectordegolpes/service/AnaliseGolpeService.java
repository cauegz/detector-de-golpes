package com.residenciafs.detectordegolpes.service;

import org.springframework.stereotype.Service;

@Service
public class AnaliseGolpeService {
    public final int QUANTIDADE_CRITERIOS=4;
    public final double VALOR_MAXIMO = QUANTIDADE_CRITERIOS * 10;

    public double calcularPorcentagem(int notaContexto, int notaConfiabilidade, int notaMeio, int notaPadraoG){
        int somaNotas = notaContexto + notaConfiabilidade + notaMeio + notaPadraoG;
        return (somaNotas/VALOR_MAXIMO) * 100;
    }

    public String classificacaoRisco(double porcentagem){
        if(porcentagem >= 75){
            return "ALTO RISCO => Porcentagem: "+porcentagem;
        }else if(porcentagem>=40){
            return "MEDIO RISCO => Porcentagem: "+porcentagem;
        }else{
            return "BAIXO RISCO => Porcentagem: "+porcentagem;
        }
    }
}
