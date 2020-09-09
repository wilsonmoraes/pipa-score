package br.com.pipa.service;

import br.com.pipa.common.exception.UnauthorizedAcessException;
import br.com.pipa.dao.UsuarioRepository;
import br.com.pipa.domain.Usuario;
import br.com.pipa.utils.BusinessValidation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static br.com.pipa.PipaScoreApp.JWT_SECRET;


@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BusinessValidation validation;

    public static Claims parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            return body;
        } catch (Exception e) {
            throw new UnauthorizedAcessException("Sua sessão expirou ou você não tem permissão para acessar esta área." +
                    " Realize o login e tente novamente");

        }
    }

    public String login(String login, String senha) {
        Usuario usuario = usuarioRepository.findUnicByLoginAndSenha(login, senha);
        validation.throwIfNull(usuario, "Usuário ou senha inválidos");

        Claims claims = Jwts.claims().setSubject(usuario.getLogin());
        claims.put("userId", String.valueOf(usuario.getId()));
        claims.put("userEmail", usuario.getLogin());

        LocalDateTime expiration = LocalDateTime.now().plusMinutes(120);
        ZonedDateTime zdt = expiration.atZone(ZoneId.systemDefault());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .setExpiration(Date.from(zdt.toInstant()))
                .compact();

    }

}
