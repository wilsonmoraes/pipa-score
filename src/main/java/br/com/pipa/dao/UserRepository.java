package br.com.pipa.dao;

import br.com.pipa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findUnicByLoginAndSenha(String login, String senha);

    @Query(value = "select u.id as user_id, u.score , (select count(1) from user u2  where u2.score >=u.score) as position from user u where u.id=:usuarioId", nativeQuery = true)
    Map<String, Object> findWithPosition(@Param("usuarioId") Long usuarioId);

    @Query(value = "select u.id as user_id, u.score, (select count(1) from user u2  where u2.score >=u.score) as position from user u order by u.score desc", nativeQuery = true)
    List<Map<String, Object>> listHighScore();


}
