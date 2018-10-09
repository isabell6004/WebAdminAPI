package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CodeBodySize;

/**
 * @author Nayeon Kim
 */
public interface CodeBodySizeRepository extends CrudRepository<CodeBodySize, Integer> {
	//CodeBodySize findTopByBodySizeID(Integer bodySizeID);
	//CodeBodySize findTopByBodySizeName(String bodySizeName);
}
