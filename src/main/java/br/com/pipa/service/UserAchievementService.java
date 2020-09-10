package br.com.pipa.service;

import br.com.pipa.dao.UserAchievementRepository;
import br.com.pipa.domain.User;
import br.com.pipa.domain.UserAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserAchievementService {


    @Autowired
    private UserAchievementRepository userAchievementRepository;


    /**
     * Método criado para auditar a pontuação
     *
     * @param user   - usuario
     * @param points - pontuação a ser auditada
     * @return
     */
    public Long auditScore(User user, Long points) {

        UserAchievement conquista = new UserAchievement();
        conquista.setName("via API");
        conquista.setDateTimeAudit(LocalDateTime.now());
        conquista.setPoints(points);
        conquista.setUser(user);
        userAchievementRepository.save(conquista);
        return conquista.getId();
    }
}
