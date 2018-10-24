package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CodeBodySize;

/**
 * @author Nayeon Kim
 */
public interface CodeBodySizeRepository extends CrudRepository<CodeBodySize, Integer> {
	List<CodeBodySize> findAll();
}
