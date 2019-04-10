package net.fashiongo.webadmin.dao.primary.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.fashiongo.webadmin.dao.primary.AdBidSettingCustom;
import net.fashiongo.webadmin.model.primary.AdBidSetting;
import net.fashiongo.webadmin.model.primary.AdPageSpot;

public class AdBidSettingCustomImpl implements AdBidSettingCustom {
	
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager primaryEntityManager;

	@Override
	public List<AdBidSetting> getFinalizeAdBidSettingTargetList() {
		
		CriteriaBuilder criteriaBuilder = primaryEntityManager.getCriteriaBuilder();
		
		CriteriaQuery<AdBidSetting> query = criteriaBuilder.createQuery(AdBidSetting.class);
		Root<AdBidSetting> adBidSetting = query.from(AdBidSetting.class);
		Join<AdBidSetting, AdPageSpot> pageSpot = adBidSetting.join("spot");
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(criteriaBuilder.equal(pageSpot.get("pageID"), 8));
		conditions.add(criteriaBuilder.lessThan(adBidSetting.get("bidEndedOn"), LocalDateTime.now()));
		conditions.add(criteriaBuilder.isNull(adBidSetting.get("finalizedOn")));

		TypedQuery<AdBidSetting> typedQuery = primaryEntityManager.createQuery(
				query.select(adBidSetting).where(conditions.toArray(new Predicate[] {})));
				
		return typedQuery.getResultList();
	}

}
