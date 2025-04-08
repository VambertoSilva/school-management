package com.vamberto.School.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// Define que essa classe é um serviço Spring (será gerenciada como um bean)
@Service
public class JwtService {

    // Chave secreta usada para assinar o token JWT.
    // Em produção, essa chave deve ser armazenada em um arquivo .env ou outro lugar seguro.
    private static final String SECRET_KEY = "minhaChaveSecretaSuperSegura12345678901234567890"; // deve ter pelo menos 256 bits

    // Método que retorna a chave de assinatura convertida para o formato correto
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Extrai o "username" (subject) do token JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrai a lista de roles (autoridades) do token
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        System.out.println(claims.get("roles", List.class)); // apenas para debug
        return claims.get("roles", List.class); // extrai a claim chamada "roles"
    }

    // Método genérico para extrair qualquer claim do token, usando uma função de resolução
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrai todas as claims do token (decodifica o JWT)
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // define a chave de assinatura
                .build()
                .parseClaimsJws(token) // faz o parse do token JWT
                .getBody(); // retorna o conteúdo (claims)
    }

    // Gera um token JWT para um usuário, incluindo as roles como claims
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities()); // adiciona as roles do usuário como claims extras
        return generateToken(claims, userDetails); // chama o outro método de geração com claims
    }

    // Gera um token JWT com claims extras
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims) // define claims extras (ex: roles)
                .setSubject(userDetails.getUsername()) // define o subject como o username
                .setIssuedAt(new Date(System.currentTimeMillis())) // data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // expira em 24 horas
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // assina com a chave e algoritmo
                .compact(); // converte tudo em uma string JWT
    }

    // Verifica se o token é válido comparando o username e verificando a expiração
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Verifica se o token já expirou
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extrai a data de expiração do token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
