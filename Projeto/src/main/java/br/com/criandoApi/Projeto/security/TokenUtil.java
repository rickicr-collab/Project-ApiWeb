package br.com.criandoApi.Projeto.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import br.com.criandoApi.Projeto.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";
    private static final long EXPIRATION = 12*60*60*1000;
    private static final String SECRET_KEY = "zK7!m9%L@2e4Y$w*QpR#t8XcUvBnM1aZ";
    private static final String EMISSOR = "devRIck";

    public static String criarToken(Usuario usuario){
        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        String token = Jwts.builder()
            .setSubject(usuario.getNome())
            .setIssuer(EMISSOR)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
        return PREFIX + token;
    }

    private static boolean isTokenValid(Date expiration){
        return expiration.after(new Date(System.currentTimeMillis()));
    }

    private static boolean isEmissorValid(String emissor){
        return emissor.equals(EMISSOR);
    }

    private static boolean isSubjectValid(String username){
        return username != null && username.length() > 0;
    }

    public static Authentication validate(HttpServletRequest request){
        String token = request.getHeader(HEADER);
        token = token.replace(PREFIX, "").trim();
        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        Jws<Claims> claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(token);
        String username = claims.getBody().getSubject();
        String issuer = claims.getBody().getIssuer();
        Date expiration = claims.getBody().getExpiration();
        if(isSubjectValid(username) && isEmissorValid(issuer) && isTokenValid(expiration)){
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        }
        return null;
    }
}
