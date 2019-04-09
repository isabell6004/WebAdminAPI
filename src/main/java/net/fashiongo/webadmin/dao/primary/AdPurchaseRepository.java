package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdPurchase;

public interface AdPurchaseRepository extends CrudRepository<AdPurchase, Integer> {

	public AdPurchase findTopByAdId(Integer adId);
}
