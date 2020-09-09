package br.com.pipa.service;

import br.com.pipa.common.exception.UnauthorizedAcessException;
import br.com.pipa.dao.UserPipaRepository;
import br.com.pipa.dao.UserPipaAchievementRepository;
import br.com.pipa.domain.UserPipa;
import br.com.pipa.domain.UsuerAchievement;
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
public class UserPipaService {


    @Autowired
    private UserPipaRepository userPipaRepository;

    @Autowired
    private UserPipaAchievementRepository userPipaAchievementRepository;

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
        UserPipa userPipa = userPipaRepository.findUnicByLoginAndSenha(login, senha);
        validation.throwIfNull(userPipa, "Usuário ou senha inválidos");

        Claims claims = Jwts.claims().setSubject(userPipa.getLogin());
        claims.put("userId", String.valueOf(userPipa.getId()));
        claims.put("userEmail", userPipa.getLogin());

        LocalDateTime expiration = LocalDateTime.now().plusMinutes(120);
        ZonedDateTime zdt = expiration.atZone(ZoneId.systemDefault());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .setExpiration(Date.from(zdt.toInstant()))
                .compact();

    }

    public void addScore(Long usuarioId, Long pontos) {

        UserPipa userPipa = userPipaRepository.findById(usuarioId).orElse(null);
        assert userPipa != null;


        UsuerAchievement conquista = new UsuerAchievement();
        conquista.setName("via API");
        conquista.setDateTimeAudit(LocalDateTime.now());
        conquista.setPoints(pontos);
        conquista.setUserPipa(userPipa);
        userPipaAchievementRepository.save(conquista);

        userPipa.setScore(userPipa.getScore() + conquista.getPoints());
        userPipaRepository.save(userPipa);


    }

    public Map<String, Object> getPosition(Long usuarioId) {
        Map<String, Object> teste = userPipaRepository.findWithPosition(usuarioId);
        if (Objects.isNull(teste)) {
            return null;
        }
        return teste;
    }

    public Map<String, Object> listHighScore() {
        List<Map<String, Object>> teste = userPipaRepository.listHighScore();
        if (Objects.isNull(teste)) {
            return null;
        }
        return new HashMap<String, Object>() {{
            put("highscores", teste);
        }};
    }

}
