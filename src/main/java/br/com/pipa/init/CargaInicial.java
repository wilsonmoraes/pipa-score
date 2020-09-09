package br.com.pipa.init;

import br.com.pipa.dao.UsuarioConquistaRepository;
import br.com.pipa.dao.UsuarioRepository;
import br.com.pipa.domain.Usuario;
import br.com.pipa.domain.UsuarioConquista;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CargaInicial implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConquistaRepository usuarioConquistaRepository;

    public void run(ApplicationArguments args) {
        if (usuarioRepository.count() == 0) {
            for (int i = 0; i <= 10; i++) {
                Usuario usuario = new Usuario();
                usuario.setLogin("usuario_" + i);
                usuario.setSenha("teste");
                usuario.setNome("Usuario " + i);
                usuario.setTotalPontos(0L);
                usuario = usuarioRepository.save(usuario);
                int z = 0;
                while (z <= 4) {
                    UsuarioConquista conquista = new UsuarioConquista();
                    conquista.setNome("aleatória");
                    conquista.setPontos(RandomUtils.nextLong(10L, 50L));
                    conquista.setUsuario(usuario);
                    usuarioConquistaRepository.save(conquista);
                    usuario.setTotalPontos(usuario.getTotalPontos() + conquista.getPontos());
                    z++;
                }
                usuarioRepository.save(usuario);
            }

        }

    }

}
