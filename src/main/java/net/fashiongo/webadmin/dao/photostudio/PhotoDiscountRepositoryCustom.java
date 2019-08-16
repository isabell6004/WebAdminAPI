package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoDiscount;

import java.util.Optional;

public interface PhotoDiscountRepositoryCustom {
    Optional<PhotoDiscount> findUsableDiscountByDiscountCode(String discountCode);
}
