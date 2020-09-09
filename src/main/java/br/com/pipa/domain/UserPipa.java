package br.com.pipa.domain;

import br.com.pipa.common.mapper.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 */
@Entity(name = "user_pipa")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPipa implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String senha;
    private String nome;
    private Long score;

}
