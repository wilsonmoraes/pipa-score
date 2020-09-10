package br.com.pipa.service;

import br.com.pipa.common.exception.UnauthorizedAcessException;
import br.com.pipa.dao.UserRepository;
import br.com.pipa.dao.UserAchievementRepository;
import br.com.pipa.domain.User;
import br.com.pipa.domain.UserAchievement;
import br.com.pipa.utils.BusinessValidation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static br.com.pipa.PipaScoreApp.JWT_SECRET;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAchievementRepository userAchievementRepository;

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
        User user = userRepository.findUnicByLoginAndSenha(login, senha);
        validation.throwIfNull(user, "Usuário ou senha inválidos");

        Claims claims = Jwts.claims().setSubject(user.getLogin());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("userEmail", user.getLogin());

        LocalDateTime expiration = LocalDateTime.now().plusMinutes(120);
        ZonedDateTime zdt = expiration.atZone(ZoneId.systemDefault());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .setExpiration(Date.from(zdt.toInstant()))
                .compact();

    }

    public void addScore(Long usuarioId, Long pontos) {

        User user = userRepository.findById(usuarioId).orElse(null);
        assert user != null;


        UserAchievement conquista = new UserAchievement();
        conquista.setName("via API");
        conquista.setDateTimeAudit(LocalDateTime.now());
        conquista.setPoints(pontos);
        conquista.setUser(user);
        userAchievementRepository.save(conquista);

        user.setScore(user.getScore() + conquista.getPoints());
        userRepository.save(user);


    }

    public Map<String, Object> getPosition(Long usuarioId) {
        Map<String, Object> teste = userRepository.findWithPosition(usuarioId);
        if (Objects.isNull(teste)) {
            return null;
        }
        return teste;
    }

    public Map<String, Object> listHighScore() {
        List<Map<String, Object>> teste = userRepository.listHighScore();
        if (Objects.isNull(teste)) {
            return null;
        }
        return new HashMap<String, Object>() {{
            put("highscores", teste);
        }};
    }

}
