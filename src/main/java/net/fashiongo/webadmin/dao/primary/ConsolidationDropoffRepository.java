package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.fashiongo.webadmin.dao.primary.custom.DropOffCustomRepository;
import net.fashiongo.webadmin.model.primary.consolidation.ConsolidatedOrder;

@Repository
public interface ConsolidationDropoffRepository extends JpaRepository<ConsolidatedOrder, Integer>, DropOffCustomRepository {

}
