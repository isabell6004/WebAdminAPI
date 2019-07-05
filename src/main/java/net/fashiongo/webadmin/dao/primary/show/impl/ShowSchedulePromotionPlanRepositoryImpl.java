package net.fashiongo.webadmin.dao.primary.show.impl;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.dao.primary.show.ShowSchedulePromotionPlanRepositoryCustom;
import net.fashiongo.webadmin.model.primary.show.QMapShowSchedulePromotionPlanVendor;
import net.fashiongo.webadmin.model.primary.show.QShowSchedulePromotionPlan;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ShowSchedulePromotionPlanRepositoryImpl implements ShowSchedulePromotionPlanRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<Integer> getShowParticipatingVendorIds(Integer showScheduleId) {

        QShowSchedulePromotionPlan qShowSchedulePromotionPlan = QShowSchedulePromotionPlan.showSchedulePromotionPlan;
        QMapShowSchedulePromotionPlanVendor qMapShowSchedulePromotionPlanVendor = QMapShowSchedulePromotionPlanVendor.mapShowSchedulePromotionPlanVendor;

        JPAQuery<Integer> jpaQuery = new JPAQuery<>(entityManager);
        return jpaQuery.select(qMapShowSchedulePromotionPlanVendor.wholeSalerID)
                .from(qShowSchedulePromotionPlan)
                    .innerJoin(qMapShowSchedulePromotionPlanVendor).on(qShowSchedulePromotionPlan.planID.eq(qMapShowSchedulePromotionPlanVendor.planID))
                .where(
                        qShowSchedulePromotionPlan.showScheduleID.eq(showScheduleId)
                        .and(qShowSchedulePromotionPlan.isOnline.isTrue())
                        .and(qMapShowSchedulePromotionPlanVendor.active.isNull().or(qMapShowSchedulePromotionPlanVendor.active.isTrue()))
                        .and(qMapShowSchedulePromotionPlanVendor.isDeleted.isNull().or(qMapShowSchedulePromotionPlanVendor.isDeleted.isFalse()))
                        .and(qMapShowSchedulePromotionPlanVendor.cancelDate.isNull())
                )
                .orderBy(qMapShowSchedulePromotionPlanVendor.wholeSalerID.asc())
                .fetch();
    }
}
