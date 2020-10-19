package net.fashiongo.webadmin.dao.primary.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QAspnetMembershipEntity;
import net.fashiongo.webadmin.data.entity.primary.QAspnetRolesEntity;
import net.fashiongo.webadmin.data.entity.primary.QAspnetUsersEntity;
import net.fashiongo.webadmin.data.entity.primary.QAspnetUsersInRolesEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorAdminAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorSettingEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import net.fashiongo.webadmin.data.model.vendor.Vendor;
import net.fashiongo.webadmin.data.model.vendor.VendorAdminAccount;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static net.fashiongo.webadmin.data.entity.primary.QVendorEntity.vendorEntity;
import static net.fashiongo.webadmin.data.entity.primary.QVendorSettingEntity.vendorSettingEntity;
import static net.fashiongo.webadmin.data.entity.primary.QVendorAddressEntity.vendorAddressEntity;
import static net.fashiongo.webadmin.model.primary.QVendorContent.vendorContent;
import static net.fashiongo.webadmin.model.primary.QVendorImageRequest.vendorImageRequest;

@Repository
public class VendorEntityRepositoryCustomImpl extends QuerydslRepositorySupport implements VendorEntityRepositoryCustom {
    public VendorEntityRepositoryCustomImpl() {
        super(VendorEntity.class);
    }

    @Override
    @PersistenceContext(unitName = "primaryEntityManager")
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Override
    public List<VendorEntity> getActiveVendors() {
        QVendorEntity vendorEntity = QVendorEntity.vendorEntity;
        QVendorSettingEntity vendorSettingEntity = QVendorSettingEntity.vendorSettingEntity;

        return from(vendorEntity)
                .select(vendorEntity)
                .leftJoin(vendorEntity.vendorSetting, vendorSettingEntity).fetchJoin()
                .where(vendorSettingEntity.statusCode.in(1,2,3))
                .fetch();
    }

    @Override
    public VendorEntity findByWholeSalerIdAndActive(Long vendorID, Boolean active) {
        QVendorEntity vendorEntity = QVendorEntity.vendorEntity;
        QVendorSettingEntity vendorSettingEntity = QVendorSettingEntity.vendorSettingEntity;

        return from(vendorEntity)
                .select(vendorEntity)
                .leftJoin(vendorEntity.vendorSetting, vendorSettingEntity).fetchJoin()
                .where(vendorEntity.vendor_id.eq(vendorID).and(vendorSettingEntity.statusCode.in(1,2,3)))
                .fetchFirst();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
    public List<VendorEntity> getEditorPickVendors() {
        return from(vendorEntity)
                .innerJoin(vendorEntity.vendorSetting, vendorSettingEntity).fetchJoin()
                .innerJoin(vendorEntity.vendorAddresses, vendorAddressEntity).fetchJoin()
                .where(vendorSettingEntity.statusCode.eq(3),  //.in(Arrays.asList(2,3)),
                        //vendorEntity.classCode.eq(2),
                        vendorEntity.vendor_id.intValue().in(
                                from(vendorContent)
                                        .select(vendorContent.wholeSalerId)
                                        .where(vendorContent.statusId.eq(2), //Approved
                                                vendorContent.isActive.eq(true),
                                                vendorContent.isDeleted.ne(true))
                                        .fetch())
                                .or(vendorEntity.vendor_id.intValue().in(
                                        from(vendorImageRequest)
                                                .select(vendorImageRequest.wholeSalerID)
                                                .where(vendorImageRequest.isApproved.eq(true),
                                                        vendorImageRequest.active.eq(true),
                                                        vendorImageRequest.vendorImageTypeID.in(Arrays.asList(8,9)/*8=Image,9=Video*/))
                                                .fetch())))
                .orderBy(vendorEntity.name.asc())
                .distinct()
                .fetch();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
    public String getCompanyNameByWholeSalerId(Integer wholeSalerId) {
        return new JPAQuery<>(getEntityManager()).select(vendorEntity.name)
                .from(vendorEntity).where(vendorEntity.vendor_id.intValue().eq(wholeSalerId)).fetchOne();
    }

    @Override
    public List<Vendor> findAllByActiveTrueAndShopActiveTrueOrderByCompanyName() {
        JPAQuery<Vendor> jpaQuery = new JPAQuery<>(getEntityManager());

        jpaQuery.select(
                Projections.constructor(
                        Vendor.class
                        , vendorEntity.vendor_id.intValue()
                        , vendorEntity.name
                )
        ).from(vendorEntity)
                .innerJoin(vendorEntity.vendorSetting, vendorSettingEntity)
                .where(vendorSettingEntity.statusCode.in(2,3))
                .orderBy(vendorEntity.name.asc());

        return jpaQuery.fetch();
    }

    @Override
    public List<Vendor> findAllByOrderActiveOrderByCompanyNameAsc(boolean isOrderActive) {
        JPAQuery<Vendor> jpaQuery = new JPAQuery<>(getEntityManager());

        jpaQuery.select(
                Projections.constructor(
                        Vendor.class
                        , vendorEntity.vendor_id.intValue()
                        , vendorEntity.name
                )
        ).from(vendorEntity)
                .innerJoin(vendorEntity.vendorSetting, vendorSettingEntity)
                .where(vendorSettingEntity.statusCode.in(3))
                .orderBy(vendorEntity.name.asc());

        return jpaQuery.fetch();
    }

    @Override
    public long countByDirNameAndNotWholeSalerID(Integer wholeSalerID, String dirName) {
        JPAQuery<Long> query = new JPAQuery<>(getEntityManager());

        query.select(vendorEntity.vendor_id.count()).from(vendorEntity).where(vendorEntity.dirname.eq(dirName).and(vendorEntity.vendor_id.intValue().ne(wholeSalerID)));

        return query.fetchFirst();
    }

    @Override
    public List<VendorAdminAccount> findVendorAdminAccountList(Integer wholeSalerID) {
        QVendorEntity W1 = new QVendorEntity("W1");
        QAspnetUsersEntity AU = new QAspnetUsersEntity("AU");
        QAspnetMembershipEntity AM1 = new QAspnetMembershipEntity("AM1");

        QVendorAdminAccountEntity V = QVendorAdminAccountEntity.vendorAdminAccountEntity;
        QVendorEntity W2 = new QVendorEntity("W2");
        QAspnetUsersEntity U = new QAspnetUsersEntity("U");
        QAspnetUsersInRolesEntity UR = new QAspnetUsersInRolesEntity("UR");
        QAspnetRolesEntity R = new QAspnetRolesEntity("R");
        QAspnetMembershipEntity AM2 = new QAspnetMembershipEntity("AM2");

        StringExpression userType = Expressions.asString("WholeSaler");
        JPAQuery<VendorAdminAccount> query1 = new JPAQuery<>(getEntityManager());
        JPAQuery<VendorAdminAccount> query2 = new JPAQuery<>(getEntityManager());

        List<VendorAdminAccount> queryResult1 = query1.select(Projections.constructor(VendorAdminAccount.class,
                W1.startingDate.as("CreatedOn"),
                W1.createdBy.as("UserID"),
                W1.firstName.concat(" ").concat(W1.lastName).as("UserName"),
                userType.as("UserType"),
                AU.lastActivityDate.as("LastActivityDate")))
                .from(W1)
                .innerJoin(AU).on(AU.userName.eq(W1.createdBy))
                .innerJoin(AM1).on(AU.userId.eq(AM1.userId))
                .where(W1.vendor_id.intValue().eq(wholeSalerID)).fetch();

        List<VendorAdminAccount> queryResult2 = query2.select(Projections.constructor(VendorAdminAccount.class,
                V.createdOn.as("CreatedOn"),
                V.userName.as("UserID"),
                V.firstName.concat(" ").concat(V.lastName).as("UserName"),
                R.roleName.as("UserType"),
                U.lastActivityDate.as("LastActivityDate")))
                .from(V)
                .innerJoin(W2).on(V.wholeSalerID.eq(W2.vendor_id.intValue()))
                .innerJoin(W2.vendorSetting, vendorSettingEntity)
                .innerJoin(U).on(V.userName.eq(U.userName))
                .innerJoin(UR).on(U.userId.eq(UR.userId))
                .innerJoin(R).on(UR.roleId.eq(R.roleId))
                .innerJoin(AM2).on(U.userId.eq(AM2.userId))
                .where(vendorSettingEntity.statusCode.in(1,2,3).and(W2.vendor_id.intValue().eq(wholeSalerID)))
                .orderBy(V.createdOn.asc())
                .fetch();

        queryResult1.addAll(queryResult2);


        return queryResult2;
    }

    @Override
    public List<VendorEntity> findAllActiveWholesalers(Boolean shopActive, Boolean orderActive, Collection<Integer> wholesalerIds) {
        JPAQuery<VendorEntity> jpaQuery = new JPAQuery<>(getEntityManager());
        jpaQuery.from(vendorEntity)
                .leftJoin(vendorEntity.vendorSetting, vendorSettingEntity).fetchJoin()
                .orderBy(vendorEntity.name.asc());
        if (Objects.nonNull(shopActive)) {
            if (shopActive) {
                jpaQuery.where(vendorSettingEntity.statusCode.in(2,3));
            } else {
                jpaQuery.where(vendorSettingEntity.statusCode.notIn(2,3));
            }
        }

        if (Objects.nonNull(orderActive)) {
            if (orderActive)
                jpaQuery.where(vendorSettingEntity.statusCode.eq(3));
            else
                jpaQuery.where(vendorSettingEntity.statusCode.ne(3));
        }

        if (!CollectionUtils.isEmpty(wholesalerIds)) {
            jpaQuery.where(vendorEntity.vendor_id.intValue().in(wholesalerIds));
        }

        return jpaQuery.fetch();
    }

    @Override
    public VendorEntity findByVendorId(Long vendorId) {
        return from(vendorEntity).select(vendorEntity).where(vendorEntity.vendor_id.eq(vendorId)).fetchOne();
    }
}
