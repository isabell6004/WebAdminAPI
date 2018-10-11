package net.fashiongo.webadmin.dao.fgem;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.fgem.EmConfiguration;

/**
 * @author Nayeon Kim
 */
public interface EmConfigurationRepository extends CrudRepository<EmConfiguration, Integer> {
	List<EmConfiguration> findAll();
	
	//EmConfiguration findOneByConfigID(Integer configID);
}
