package com.devnot.CampistsIbod.infra.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {


    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 3600L;

        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("devnot")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scopes)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getSubjectFromTokenWithoutValidation(String token) {
        try {

            if (token.startsWith("Bearer")) {
                token = token.substring(7);
            }
            // Separar o payload do token (parte entre os dois pontos)
            String[] parts = token.split("\\.");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Token inválido");
            }

            // Decodificar o payload (Base64Url)
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

            // Converter JSON em Map
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> claims = objectMapper.readValue(payload, Map.class);

            // Retornar o "sub" (subject)
            return (String) claims.get("sub");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao decodificar o token", e);
        }
    }
}
