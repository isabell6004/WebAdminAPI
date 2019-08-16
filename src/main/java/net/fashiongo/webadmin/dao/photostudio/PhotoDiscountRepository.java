package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoDiscount;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoDiscountRepository extends CrudRepository<PhotoDiscount, Integer>, PhotoDiscountRepositoryCustom {

    List<PhotoDiscount> findAll(Sort paramSort);

    boolean existsByDiscountCode(String discountCode);
}
