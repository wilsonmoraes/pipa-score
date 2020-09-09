package br.com.pipa.dao;

import br.com.pipa.domain.UsuarioConquista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UsuarioConquistaRepository extends JpaRepository<UsuarioConquista, Long> {


}
