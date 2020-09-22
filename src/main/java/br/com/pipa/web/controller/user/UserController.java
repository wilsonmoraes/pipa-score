package br.com.pipa.web.controller.user;

import br.com.pipa.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint utilizado para adicionar uma pontuação ao usuário
     *
     * @param request {@link PostAddScoreDto}
     * @return ResponseEntity
     */
    @ApiOperation("Adiciona uma pontuação ao usuário")
    @PostMapping
    public ResponseEntity<Void> addScore(@RequestBody PostAddScoreDto request) {
        userService.addScore(request.getUserId(), request.getPoints());

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    /**
     * Método criado para retornar qual é a posição e pontuação atual do usuário
     *
     * @param usuarioId - identificador do usuário
     * @return Map
     */
    @GetMapping(value = "{usuarioId}/position")
    @ApiOperation("Retorna a posição do usuário e sua pontuação atual")
    public ResponseEntity<Map<String, Object>> getPosition(@PathVariable(value = "usuarioId") Long usuarioId) {
        Map<String, Object> retorno = userService.getPosition(usuarioId);

        return ResponseEntity.status(Objects.isNull(retorno) ? HttpStatus.NOT_FOUND : HttpStatus.FOUND)
                .body(retorno);
    }

    /**
     * Método criado para listar os usuários por pontuação
     *
     * @return Map
     */
    @GetMapping(value = "highscorelist")
    @ApiOperation("Retorna uma lista de usuários pela  posição com relação aos demais e sua pontuação atual")
    public ResponseEntity<Map<String, Object>> listHighScore() {
        Map<String, Object> retorno = userService.listHighScore();

        return ResponseEntity.status(Objects.isNull(retorno) ? HttpStatus.NOT_FOUND : HttpStatus.FOUND)
                .body(retorno);
    }

}
