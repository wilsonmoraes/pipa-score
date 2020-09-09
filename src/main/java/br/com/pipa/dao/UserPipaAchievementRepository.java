package br.com.pipa.dao;

import br.com.pipa.domain.UsuerAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserPipaAchievementRepository extends JpaRepository<UsuerAchievement, Long> {


}
