package com.residenciafs.detectordegolpes.config;

import com.google.genai.Client;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpRetryOptions;
import com.residenciafs.detectordegolpes.exception.APIKeyInvalid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GeminiConfig {

    private static final int TIMEOUT_MILLIS = 30_000;

    @Bean
    public Client googleGenAiClient(
            @Value("${spring.ai.google.genai.api-key:}") String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new APIKeyInvalid("GEMINI_API_KEY deve ser definida em um arquivo .env");
        }

        HttpRetryOptions retryOptions = HttpRetryOptions.builder()
                .attempts(2)
                .initialDelay(0.5)
                .maxDelay(1.0)
                .expBase(2.0)
                .jitter(0.2)
                .build();

        HttpOptions httpOptions = HttpOptions.builder()
                .timeout(TIMEOUT_MILLIS)
                .retryOptions(retryOptions)
                .build();

        return Client.builder()
                .apiKey(apiKey)
                .httpOptions(httpOptions)
                .build();
    }
}
