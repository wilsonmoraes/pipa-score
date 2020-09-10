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
@Entity(name = "user_achievement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAchievement implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Long points;

    private String name;

    @Column
    private LocalDateTime dateTimeAudit;

}
