package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.WholeSalerRating;

public interface WholeSalerRatingRepository extends CrudRepository<WholeSalerRating, Integer> {
	WholeSalerRating findOneByWholeSalerRatingID(Integer wholeSalerRatingID);
}
