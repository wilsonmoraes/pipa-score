package br.com.pipa.dao;

import br.com.pipa.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUnicByLoginAndSenha(String login, String senha);

    @Query(value = "select u.id, u.login, u.nome,(select count(1) from usuario u2  where u2.total_pontos >=u.total_pontos) from usuario u where u.id=:usuarioId", nativeQuery = true)
    Object findWithPosition(@Param("usuarioId") String usuarioId);


}
