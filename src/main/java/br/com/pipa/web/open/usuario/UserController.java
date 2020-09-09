package br.com.pipa.web.open.usuario;

import br.com.pipa.service.UserPipaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping(value = "open/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserPipaService userPipaService;

    @ApiOperation("Adiciona uma pontuação ao usuário")
    @PostMapping
    public ResponseEntity<Void> addScore(@RequestBody PostScoreDto request) {
        userPipaService.addScore(request.getUserId(), request.getPoints());

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping(value = "{usuarioId}/position")
    @ApiOperation("Retorna a posição do usuário e sua pontuação atual")
    public ResponseEntity<Map<String, Object>> getPosition(@PathVariable(value = "usuarioId") Long usuarioId) {
        Map<String, Object> retorno = userPipaService.getPosition(usuarioId);

        return ResponseEntity.status(Objects.isNull(retorno) ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .body(retorno);
    }

    @GetMapping(value = "highscorelist")
    @ApiOperation("Retorna uma lista de usuários pela  posição com relação aos demais e sua pontuação atual")
    public ResponseEntity<Map<String, Object>> listHighScore() {
        Map<String, Object> retorno = userPipaService.listHighScore();

        return ResponseEntity.status(Objects.isNull(retorno) ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .body(retorno);
    }

}
