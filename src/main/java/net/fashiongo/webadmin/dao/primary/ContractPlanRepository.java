package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.ContractPlan;

public interface ContractPlanRepository extends CrudRepository<ContractPlan, Integer>{
	List<ContractPlan> findAll();
}
