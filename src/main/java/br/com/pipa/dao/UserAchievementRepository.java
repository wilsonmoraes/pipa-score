package br.com.pipa.dao;

import br.com.pipa.domain.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {


}
