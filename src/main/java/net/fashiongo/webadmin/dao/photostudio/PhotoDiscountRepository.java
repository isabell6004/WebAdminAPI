package net.fashiongo.webadmin.dao.photostudio;

import java.util.List;

import net.fashiongo.webadmin.model.photostudio.PhotoDiscount;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface PhotoDiscountRepository extends CrudRepository<PhotoDiscount, Integer>{
		
	List<PhotoDiscount> findAll(Sort paramSort);

	boolean existsByDiscountCode(String discountCode);
}
