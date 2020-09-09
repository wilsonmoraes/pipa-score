package br.com.pipa.init;

import br.com.pipa.dao.UserPipaAchievementRepository;
import br.com.pipa.dao.UserPipaRepository;
import br.com.pipa.domain.UserPipa;
import br.com.pipa.domain.UsuerAchievement;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CargaInicial implements ApplicationRunner {

    @Autowired
    private UserPipaRepository userPipaRepository;

    @Autowired
    private UserPipaAchievementRepository userPipaAchievementRepository;

    public void run(ApplicationArguments args) {
        if (userPipaRepository.count() == 0) {
            for (int i = 0; i <= 10; i++) {
                UserPipa userPipa = new UserPipa();
                userPipa.setLogin("usuario_" + i);
                userPipa.setSenha("teste");
                userPipa.setNome("Usuario " + i);
                userPipa.setScore(0L);
                userPipa = userPipaRepository.save(userPipa);
                int z = 0;
                while (z <= 4) {
                    UsuerAchievement conquista = new UsuerAchievement();
                    conquista.setName("aleatória");
                    conquista.setPoints(RandomUtils.nextLong(10L, 50L));
                    conquista.setUserPipa(userPipa);
                    userPipaAchievementRepository.save(conquista);
                    userPipa.setScore(userPipa.getScore() + conquista.getPoints());
                    z++;
                }
                userPipaRepository.save(userPipa);
            }

        }

    }

}
