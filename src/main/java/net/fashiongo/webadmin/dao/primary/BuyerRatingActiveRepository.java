package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.RetailerRating;

public interface BuyerRatingActiveRepository extends CrudRepository<RetailerRating, Integer> {
	RetailerRating findOneByRetailerRatingID(Integer retailerRatingID);
}
