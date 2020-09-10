package br.com.pipa.service;

import br.com.pipa.dao.UserRepository;
import br.com.pipa.domain.User;
import br.com.pipa.utils.BusinessValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserAchievementService userAchievementService;

    @Autowired
    private BusinessValidation validation;


    /**
     * Método criado para adicionar uma pontuação ao usuário
     *
     * @param usuarioId - identificador do usuário
     * @param points    - ponto do usuário
     * @return
     */
    public Long addScore(Long usuarioId, Long points) {

        User user = userRepository.findById(usuarioId).orElse(null);
        assert user != null;

        Long conquistaId = userAchievementService.auditScore(user, points);
        user.setScore(user.getScore() + points);
        userRepository.save(user);
        return conquistaId;


    }


    /**
     * Método criado para retornar qual é a posição e pontuação atual do usuário
     *
     * @param usuarioId - identificador do usuário
     * @return Map
     */
    public Map<String, Object> getPosition(Long usuarioId) {
        Map<String, Object> teste = userRepository.findWithPosition(usuarioId);
        if (Objects.isNull(teste)) {
            return null;
        }
        return teste;
    }

    /**
     * Método criado para listar os usuários por pontuação
     *
     * @return Map
     */
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
