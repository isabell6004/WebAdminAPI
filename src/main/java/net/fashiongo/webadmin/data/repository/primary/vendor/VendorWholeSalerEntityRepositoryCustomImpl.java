package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QAspnetMembershipEntity;
import net.fashiongo.webadmin.data.entity.primary.QCategoryEntity;
import net.fashiongo.webadmin.data.entity.primary.QCountEntity;
import net.fashiongo.webadmin.data.entity.primary.QSimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorAdminAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorLambsKeyEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.SimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.QWholesalerCompanyEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.WholesalerCompanyEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VendorWholeSalerEntityRepositoryCustomImpl implements VendorWholeSalerEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<VendorDetailInfo> findAllByID(Integer wholeSalerID) {
        JPAQuery<VendorDetailInfo> query = new JPAQuery<>(entityManager);
        JPAQuery<String> subQuery = new JPAQuery<>(entityManager);
        JPAQuery<Integer> subsubQuery = new JPAQuery<>(entityManager);

        QWholeSalerEntity T = QWholeSalerEntity.wholeSalerEntity;
        QCategoryEntity C = QCategoryEntity.categoryEntity;
        QAspnetMembershipEntity ASPM = QAspnetMembershipEntity.aspnetMembershipEntity;
        QCountEntity COUNT = QCountEntity.countEntity;
        QVendorAdminAccountEntity VAA = QVendorAdminAccountEntity.vendorAdminAccountEntity;
        QVendorLambsKeyEntity VLK = QVendorLambsKeyEntity.vendorLambsKeyEntity;

        Integer referenceID = subsubQuery.select(COUNT.referenceID).from(COUNT).where(COUNT.countTypeID.eq(2).and(COUNT.entityID.eq(wholeSalerID))).orderBy(COUNT.count.desc()).fetchFirst();
        String categoryName = subQuery.select(C.categoryName).from(C).where(eqReferenceID(referenceID, C)).fetchFirst();
        StringExpression cateName = Expressions.asString(categoryName);

        query.select(Projections.constructor(VendorDetailInfo.class,
                T.wholeSalerID, T.sortNo, T.startingDate, T.companyName, T.regCompanyName, T.dirName, T.codeName, T.firstName, T.lastName, T.description,
                T.billStreetNo, T.billCity, T.billState, T.billZipcode, T.billCountry, T.billPhone, T.billFax, T.streetNo, T.city, T.state, T.zipcode, T.country, T.phone, T.email, T.fax, T.memo,
                T.webSite, T.userId, T.pwd, T.webSiteActive, T.slActive, T.reportActive, T.catalogActive, T.lambsActive, T.group1, T.group2, T.dm, T.posUse, T.mainPage, T.titlePageMemo, T.wsaPolicy,
                T.wholeSalerTitlePage, T.onSale, T.newCustYN, T.goodUpYN, T.minTQYN, T.minTQ, T.minDollarYN, T.minDollar, T.minECQYN, T.minECQ, T.qtySeqYN, T.minPolicyUseYN, T.orderNotice,
                T.compCharge, T.ratio, T.autoRActive, T.minCharge, T.minAmount, T.isMonthly, T.isYearly, T.yearlyAmount, T.noticeToAll, T.asKnownAs, T.honote1, T.honote2, T.honote3, T.honote4,
                T.actualOpenDate, T.billingNote2, T.specialNote1, T.specialNote2, T.adv1, T.adv2, T.advertiseYN, T.actualOpen, T.ownerCountry, T.contractExpireDate, T.billReviewHoLee, T.openDate,
                T.compsolutionNo, T.retailerYes, T.retailerBlockList, T.retailerOpenList, T.dateTimeModified, T.prePackYN, T.salesItem, T.hotItems, T.promotionalItems, T.billStreetNo2, T.streetNo2,
                T.itemLocation, T.lastUser, T.lastModifiedDateTime, T.minTQYNStyle, T.minTQStyle, T.billingNote1, T.billingYN, T.inHouseMemo, T.sizeID, T.packID, T.districtID, T.fgPlan,
                T.webSiteLinkCount, T.howKnownType, T.howKnownOther, T.discountYN, T.insertedWhichApp, T.allowImage2Anony, T.maxPictureQty, T.allowImmediateShopping, T.businessCategory,
                T.imageServerID, T.contactPerson, T.companyTypeID, T.establishedYear, T.pictureMain, T.pictureLogo, T.sizeChart, T.madeIn, T.productSortByLastUpdate, T.active, T.shopActive,
                T.orderActive, T.billCountryID, T.countryID, T.billPhone2, T.phone2, T.creditCardAccessPassword, T.adminAccountCap, T.defaultSizeID, T.defaultPackID, T.defaultFabricDescriptionID,
                T.manageInventory, T.allowAccess2Y3, T.sm_Facebook, T.sm_Twitter, T.sm_Youtube, T.blog, T.minOrderByAmount, T.minOrderByQty, T.extraCharge, T.extraChargeAmountFrom, T.extraChargeAmountTo,
                T.industryType, T.orderActiveLock, T.activeOld, T.shopActiveOld, T.minOrderAmount, T.minOrderAmountYN, T.minTQAllYN, T.minTQAll, T.itemUploadCap, T.defaultMadeInCountryID, T.defaultLabelID,
                T.defaultInventoryStatusID, T.pictureMain2, T.showFeedback, T.consolidationYN, T.defaultVendorID, T.adminWebServerID, T.wholeSalerGUID, T.fashionGoExclusive, T.sizeChartImage,
                T.blockPolicy, T.sm_Instagram, T.chargedByCreditCard,
                cateName,
                ExpressionUtils.as(JPAExpressions.select(ASPM.isLockedOut).from(ASPM).where(ASPM.userId.eq(T.wholeSalerGUID)),"IsLockedOut"),
                ExpressionUtils.as(JPAExpressions.select(ASPM.lastLockoutDate).from(ASPM).where(ASPM.userId.eq(T.wholeSalerGUID)), "LstLockoutDate"),
                ExpressionUtils.as(JPAExpressions.select(ASPM.isLockedOut.count()).from(ASPM).where(ASPM.userId.in(JPAExpressions.select(VAA.userGUID).from(VAA).where(VAA.wholeSalerID.eq(wholeSalerID).and(ASPM.isLockedOut.eq(true))))),"IsLockedOut2"),
                T.useCreditCardPaymentService, T.transactionFeeRate1, T.transactionFeeRate2, T.transactionFeeRate1Intl, T.transactionFeeRate2Intl, T.transactionFeeFixed,
                T.commissionRate, T.billingEmail1, T.billingEmail2, T.billingEmail3, T.showRoomStreetNo, T.showRoomCity, T.showRoomCountry, T.showRoomState,
                T.showRoomZipcode, T.showRoomPhone, T.showRoomFax,
                ExpressionUtils.as(JPAExpressions.select(VLK.wholeSalerID.count()).from(VLK).where(VLK.wholeSalerID.eq(wholeSalerID)), "elambsuser"),
                T.isADBlock.as("IsADBlock")
                )).from(T)
                .where(T.wholeSalerID.eq(wholeSalerID));

        return query.fetch();
    }

    @Override
    public WholeSalerEntity findOneByID(Integer wholeSalerID) {
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;

        JPAQuery<WholeSalerEntity> query = new JPAQuery<>(entityManager);

        query.select(W).from(W).where(W.wholeSalerID.eq(wholeSalerID));

        return query.fetchFirst();
    }

    @Override
    public List<WholesalerCompanyEntity> findAllActive() {
        QWholesalerCompanyEntity W = QWholesalerCompanyEntity.wholesalerCompanyEntity;
        JPAQuery<WholesalerCompanyEntity> query = new JPAQuery<>(entityManager);

        query.select(W).from(W).where(W.active.eq(true));

        return query.fetch();
    }

    private BooleanExpression eqReferenceID(Integer referenceID, QCategoryEntity C) {
        if (referenceID == null) {
            return null;
        }
        return C.categoryId.eq(referenceID);
    }
}
