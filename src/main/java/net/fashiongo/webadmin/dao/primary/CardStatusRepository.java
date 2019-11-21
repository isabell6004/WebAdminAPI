package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.model.primary.CardStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardStatusRepository extends JpaRepository<CardStatus, Integer> {
}
