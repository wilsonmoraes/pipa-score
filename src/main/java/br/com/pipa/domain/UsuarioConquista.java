package br.com.pipa.domain;

import br.com.pipa.common.mapper.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 */
@Entity(name = "usuario_conquista")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioConquista implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    private Long pontos;

    private String nome;

    @Column
    private LocalDateTime dateTimeAudit;

}
