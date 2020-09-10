package br.com.pipa.init;

import br.com.pipa.dao.UserAchievementRepository;
import br.com.pipa.dao.UserRepository;
import br.com.pipa.domain.User;
import br.com.pipa.domain.UserAchievement;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CargaInicial implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAchievementRepository userAchievementRepository;

    public void run(ApplicationArguments args) {
        if (userRepository.count() == 0) {
            for (int i = 0; i <= 10; i++) {
                User user = new User();
                user.setLogin("usuario_" + i);
                user.setSenha("teste");
                user.setNome("Usuario " + i);
                user.setScore(0L);
                user = userRepository.save(user);
                int z = 0;
                while (z <= 4) {
                    UserAchievement conquista = new UserAchievement();
                    conquista.setName("aleatória");
                    conquista.setPoints(RandomUtils.nextLong(10L, 50L));
                    conquista.setUser(user);
                    userAchievementRepository.save(conquista);
                    user.setScore(user.getScore() + conquista.getPoints());
                    z++;
                }
                userRepository.save(user);
            }

        }

    }

}
