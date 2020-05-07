package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.entity.primary.vendor.QWholesalerCompanyEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.WholesalerCompanyEntity;
import net.fashiongo.webadmin.data.model.common.VendorsCompanyName;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VendorWholeSalerEntityRepositoryCustomImpl implements VendorWholeSalerEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Autowired
    private QueryDSLSQLFunctions queryDSLSQLFunctions;

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
        QVendorSeoEntity VS = QVendorSeoEntity.vendorSeoEntity;

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
                T.isADBlock.as("IsADBlock"),
                T.sourceType,
                ExpressionUtils.as(JPAExpressions.select(VS.metaKeyword).from(VS).where(VS.vendorId.eq(T.wholeSalerID)),"MetaKeyword"),
                ExpressionUtils.as(JPAExpressions.select(VS.metaDescription).from(VS).where(VS.vendorId.eq(T.wholeSalerID)),"MetaDescription")
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

    @Override
    public Long countByCodeNameAndNotWholeSalerID(Integer wholeSalerID, String codeName) {
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;
        JPAQuery<Long> query = new JPAQuery<>(entityManager);

        query.select(W.wholeSalerID.count()).from(W).where(W.codeName.eq(codeName).and(W.wholeSalerID.ne(wholeSalerID)));

        return query.fetchFirst();
    }

    @Override
    public List<VendorGroupingSelected> findListVendorGroupingSelect(Integer wholeSalerID, Integer[] companyType, String keyword, ArrayList<Integer> categorys, String alphabet, String vendorType) {
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;
        QMapWholeSalerGroupEntity M = QMapWholeSalerGroupEntity.mapWholeSalerGroupEntity;
        QCountEntity count = QCountEntity.countEntity;

        StringExpression selectChk = Expressions.asString("1").as("SelectChk");

        StringPath pathVendorType = Expressions.stringPath(W, vendorType);

        MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
        JPASQLQuery<VendorGroupingSelected> jpasqlQuery = new JPASQLQuery<VendorGroupingSelected>(entityManager, mssqlServer2012Templates);

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression expression = Expressions.asNumber(1).eq(constant);

        expression = expression.and(M.wholeSalerID.eq(wholeSalerID));
        if (companyType != null && companyType.length != 0) {
            expression = expression.and(W.companyTypeID.in(companyType));
        }
        if (categorys != null && categorys.size() != 0) {
            expression = expression.and(M.wholeSalerID2.in(
                    JPAExpressions.select(count.entityID).from(count).where(count.countTypeID.eq(2).and(count.referenceID.in(categorys)))));
        }
        if (StringUtils.isNotEmpty(alphabet)) {
            expression = expression.and(W.companyName.startsWith(alphabet));
        }
        if (StringUtils.isNotEmpty(keyword) && !keyword.equals(" ")) {
            expression = expression.and(pathVendorType.startsWith(keyword));
        }

        jpasqlQuery.select(Projections.constructor(VendorGroupingSelected.class,
                M.mapID,
                M.wholeSalerID2.as("WholeSalerID"),
                W.companyName,
                W.companyTypeID,
                selectChk))
                .from(W)
                .innerJoin(M).on(W.wholeSalerID.eq(M.wholeSalerID2))
                .where(expression)
                .orderBy(W.companyName.asc());

        return jpasqlQuery.fetch();
    }

    @Override
    public List<VendorGroupingUnselect> findListVendorGroupingUnSelect(Integer wholeSalerID, Integer[] companyType, String keyword, ArrayList<Integer> categorys, String alphabet, String vendorType) {
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;
        QMapWholeSalerGroupEntity M = QMapWholeSalerGroupEntity.mapWholeSalerGroupEntity;
        QCountEntity count = QCountEntity.countEntity;

        StringExpression selectChk = Expressions.asString("0").as("SelectChk");
        StringPath pathVendorType = Expressions.stringPath(W, vendorType);

        MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
        JPASQLQuery<VendorGroupingUnselect> jpasqlQuery = new JPASQLQuery<VendorGroupingUnselect>(entityManager, mssqlServer2012Templates);

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression expression = Expressions.asNumber(1).eq(constant);

        expression = expression.and(W.wholeSalerID.ne(wholeSalerID));
        if (companyType != null && companyType.length != 0) {
            expression = expression.and(W.companyTypeID.in(companyType));
        }
        if (categorys != null && categorys.size() != 0) {
            expression = expression.and(M.wholeSalerID2.in(
                    JPAExpressions.select(count.entityID).from(count).where(count.countTypeID.eq(2).and(count.referenceID.in(categorys)))
            ));
        }
        if (StringUtils.isNotEmpty(alphabet)) {
            expression = expression.and(W.companyName.startsWith(alphabet));
        }
        if (StringUtils.isNotEmpty(keyword) && !keyword.equals(" ")) {
            expression = expression.and(pathVendorType.startsWith(keyword));
        }

        ArrayList<Integer> companyTypes = new ArrayList<Integer>();
        companyTypes.add(1);
        companyTypes.add(2);
        companyTypes.add(3);

        jpasqlQuery.select(Projections.constructor(VendorGroupingUnselect.class,
                W.wholeSalerID.as("WholeSalerID"),
                W.companyName,
                W.companyTypeID,
                selectChk))
                .from(W)
                .leftJoin(M).on(M.wholeSalerID.eq(wholeSalerID).and(W.wholeSalerID.eq(M.wholeSalerID2)))
                .where(M.wholeSalerID.isNull().and(W.companyTypeID.in(companyTypes)).and(W.active.eq(true)).and(W.shopActive.eq(true)).and(W.orderActive.eq(true))
                        .and(expression))
                .orderBy(W.companyName.asc());

        return jpasqlQuery.fetch();
    }

    @Override
    public List<VendorsCompanyName> findVendors() {
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;
        JPAQuery<VendorsCompanyName> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(VendorsCompanyName.class,
                W.wholeSalerID,
                W.companyName))
        .from(W)
        .where(W.active.eq(true))
        .orderBy(W.companyName.asc());

        return query.fetch();
    }

    @Override
    @Transactional
    public Page<VendorList> getVendorListWithCount(GetVendorListParameter param) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");

        Integer pageNum = param.getPageNum() == null ? 0 : param.getPageNum();
		Integer pageSize = param.getPageSize() == null ? 0 : param.getPageSize();
		String userID = StringUtils.isEmpty(param.getUserID()) ? null : param.getUserID();
		Boolean userIDPartialMatch = param.getUserIdPartialMatch();
		String companyName = StringUtils.isEmpty(param.getCompanyName()) ? null : param.getCompanyName();
		boolean companyNamePartialMatch = true;
		Boolean companyNameStartsWith = null;
		String firstName = null;
		Boolean firstNamePartialMatch = null;
		String lastName = null;
		Boolean lastNamePartialMatch = null;
		LocalDateTime createdOnFrom = null;
		LocalDateTime createOnTo = null;
		Boolean active = null;
		Boolean shopActive = null;
		Boolean orderActive = null;
		String orderBy = StringUtils.isEmpty(param.getOrderBy()) ? null : param.getOrderBy();
		String oldCompanyName = StringUtils.isEmpty(param.getOldCompanyName()) ? null : param.getOldCompanyName();
		Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
		String companyType = StringUtils.isEmpty(param.getCompanyType()) ? "" : param.getCompanyType();

		List<Integer> companyTypeArray = new ArrayList<>();
		if (StringUtils.isNotEmpty(companyType)) {
            for (String type : companyType.split(",")) {
                if (StringUtils.isNotEmpty(type))
                companyTypeArray.add(Integer.valueOf(type));
            }
        }

		String location = StringUtils.isEmpty(param.getLocation()) ? null : param.getLocation();
		String state = StringUtils.isEmpty(param.getState()) ? null : param.getState();
		String country = StringUtils.isEmpty(param.getCountry()) ? null : param.getCountry();
		String typeOfContract = StringUtils.isEmpty(param.getTypeOfContract()) ? null : param.getTypeOfContract();
		String photoplan = StringUtils.isEmpty(param.getPhotoPlan()) ? null : param.getPhotoPlan();
		String chooseType = StringUtils.isEmpty(param.getChooseType()) ? null : param.getChooseType();
		String commission = StringUtils.isEmpty(param.getCommission()) ? null : param.getCommission();

		String actualOpenFrom = StringUtils.isEmpty(param.getActualopenfrom()) ? null : param.getActualopenfrom();
		String actualOpenTo = StringUtils.isEmpty(param.getActualopento()) ? null : param.getActualopento();

        Timestamp actualOpenFromTimestamp = null;
        if (StringUtils.isNotEmpty(actualOpenFrom)) {
            actualOpenFromTimestamp = Timestamp.valueOf(LocalDateTime.parse(actualOpenFrom, formatter));
        }
        Timestamp actualOpenToTimestamp = null;
        if (StringUtils.isNotEmpty(actualOpenTo)) {
            actualOpenToTimestamp = Timestamp.valueOf(LocalDateTime.parse(actualOpenTo, formatter));
        }

		BigDecimal avgOrderAmountFrom = param.getAvgorderamountfrom();
		BigDecimal avgOrderAmountTo = param.getAvgorderamountto();

		String checkoutFrom = StringUtils.isEmpty(param.getCheckoutfrom()) ? null : param.getCheckoutfrom();
        String checkoutTo = StringUtils.isEmpty(param.getCheckoutto()) ? null : param.getCheckoutto();

        Timestamp checkoutFromTimestamp = null;
		if (StringUtils.isNotEmpty(checkoutFrom)) {
            checkoutFromTimestamp = Timestamp.valueOf(LocalDateTime.parse(checkoutFrom, formatter));
        }
        Timestamp checkoutToTimestamp = null;
        if (StringUtils.isNotEmpty(checkoutTo)) {
            checkoutToTimestamp = Timestamp.valueOf(LocalDateTime.parse(checkoutTo, formatter));
        }

		BigDecimal adSpentAmountFrom = param.getAdspentamountfrom();
		BigDecimal adSpentAmountTo = param.getAdspentamountto();

		String adFrom = StringUtils.isEmpty(param.getAdfrom()) ? null : param.getAdfrom();
		String adTo = StringUtils.isEmpty(param.getAdto()) ? null : param.getAdto();

        Timestamp adFromTimestamp = null;
        if (StringUtils.isNotEmpty(adFrom)) {
            adFromTimestamp = Timestamp.valueOf(LocalDateTime.parse(adFrom, formatter));
        }
        Timestamp adToTimestamp = null;
        if (StringUtils.isNotEmpty(adTo)) {
            adToTimestamp = Timestamp.valueOf(LocalDateTime.parse(adTo, formatter));
        }

		Integer recurringFrom = param.getRecurringfrom();
		Integer recurringTo = param.getRecurringto();
        //contractexpiredatefrom
        String contractExpiredateFrom = StringUtils.isEmpty(param.getContractexpiredatefrom()) ? null : param.getContractexpiredatefrom();
        String contractExpiredateTo = StringUtils.isEmpty(param.getContractexpiredateto()) ? null : param.getContractexpiredateto();

        Timestamp contractExpiredateFromTimestamp = null;
        if (StringUtils.isNotEmpty(contractExpiredateFrom)) {
            contractExpiredateFromTimestamp = Timestamp.valueOf(LocalDateTime.parse(contractExpiredateFrom, formatter));
        }
        Timestamp contractExpiredateToTimestamp = null;
        if (StringUtils.isNotEmpty(contractExpiredateTo)) {
            contractExpiredateToTimestamp = Timestamp.valueOf(LocalDateTime.parse(contractExpiredateTo, formatter));
        }

		Integer categoryModel = param.getCategoryModel() == null ? 0 : param.getCategoryModel();
		Integer status = param.getStatus() == null ? 0 : param.getStatus();
		Integer assignedUser = param.getAssignedUser() == null ? 0 : param.getAssignedUser();
		Integer fgExclusiveType = param.getFgExclusiveType();
		Integer sourcetype = param.getSourcetype() == null ? 0 : param.getSourcetype();

		if (wholeSalerID == 0) wholeSalerID = null;

        int offset = pageSize * (pageNum - 1);
        int limit = pageSize;

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression filter = Expressions.asNumber(1).eq(constant);

        QVendorBlockedEntity VB = QVendorBlockedEntity.vendorBlockedEntity;
        QLogVendorHoldEntity LVH = QLogVendorHoldEntity.logVendorHoldEntity;
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;
        QMapWholeSalerGroupEntity MWG = QMapWholeSalerGroupEntity.mapWholeSalerGroupEntity;
        QVendorContractEntity VC = QVendorContractEntity.vendorContractEntity;
        QCountEntity C = QCountEntity.countEntity;
        QVendorNameHistoryEntity V = QVendorNameHistoryEntity.vendorNameHistoryEntity;

        MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
        JPASQLQuery<VendorList> jpasqlQuery = new JPASQLQuery<VendorList>(entityManager, mssqlServer2012Templates);

        Timestamp nowTimestamp = Timestamp.valueOf(LocalDateTime.now());

        jpasqlQuery.select(Projections.constructor(VendorList.class,
                SQLExpressions.rowNumber().over().orderBy(W.startingDate.desc()).as("rowno"),
                W.contractExpireDate,
                W.wholeSalerID,
                W.companyName,
                W.companyTypeID,
                W.firstName,
                W.lastName,
                W.email,
                W.userId,
                W.active,
                W.shopActive,
                W.orderActive,
                W.startingDate,
                W.lastModifiedDateTime,
                V.nameHistory,
                ExpressionUtils.as(SQLExpressions.select(MWG.mapID.count()).from(MWG).where(MWG.wholeSalerID.eq(W.wholeSalerID)),"Grouped"),
                W.businessCategory,
                Expressions.asNumber(0).as("CheckBox"),
                Expressions.asString("").as("LinkCheck"),
                ExpressionUtils.as(SQLExpressions.select(VB.blockId.count()).from(VB).where(VB.wholeSalerId.eq(W.wholeSalerID)), "BlockedCheck"),
                ExpressionUtils.as(SQLExpressions.select(LVH.logID.count()).from(LVH).where(
                        LVH.active.eq(true).and(
                                LVH.holdFrom.loe(nowTimestamp).and(LVH.holdTo.goe(nowTimestamp)))
                                .and(LVH.wholeSalerID.eq(W.wholeSalerID))), "HoldCheck"),
                W.billCountryID,
                W.billState,
                VC.contractTypeID,
                VC.photoPlanID,
                VC.useModel,
                VC.commissionRate,
                W.fashionGoExclusive,
                W.sourceType
                ))
                .from(W)
                .leftJoin(V).on(W.wholeSalerID.eq(V.wholeSalerID))
                .leftJoin(VC).on(W.wholeSalerID.eq(VC.wholeSalerID).and(VC.vendorContractID.in(SQLExpressions.select(VC.vendorContractID.max()).from(VC).groupBy(VC.wholeSalerID))));

        if (avgOrderAmountFrom != null || avgOrderAmountTo != null || checkoutFrom != null || checkoutTo != null) {
            SimplePath<Object> pathO = Expressions.path(Object.class, "O");

            QOrdersEntity O = new QOrdersEntity("O");
            NumberPath<Double> pathO_totalOrderAmount = Expressions.numberPath(Double.class, pathO, "totalorderamount");

            BooleanExpression subFilter = O.orderStatusID.notIn(0);
            JPASQLQuery queryOrder = new JPASQLQuery(entityManager,mssqlServer2012Templates);

            if (wholeSalerID != null) {
                subFilter = subFilter.and(O.wholeSalerID.eq(wholeSalerID));
            }

            if (checkoutFrom != null) {
                subFilter = subFilter.and(O.checkOutDate.goe(checkoutFromTimestamp));
            }

            if (checkoutTo != null) {
                subFilter = subFilter.and(O.checkOutDate.loe(checkoutToTimestamp));
            }

            queryOrder.select(O.wholeSalerID,O.totalAmountWSC.sum().divide(O.orderID.count()).as("totalorderamount")).from(O).where(subFilter).groupBy(O.wholeSalerID);
            jpasqlQuery.innerJoin(queryOrder,pathO).on(W.wholeSalerID.eq(O.wholeSalerID));

            if (avgOrderAmountFrom != null) {
                filter = filter.and(pathO_totalOrderAmount.goe(avgOrderAmountFrom));
            }

            if (avgOrderAmountTo != null) {
                filter = filter.and(pathO_totalOrderAmount.loe(avgOrderAmountTo));
            }
        }

        if (adSpentAmountFrom != null || adSpentAmountTo != null || adFrom != null || adTo != null) {
            SimplePath<Object> pathAV = Expressions.path(Object.class, "AV");

            QAdVendorEntity AV = new QAdVendorEntity("AV");
            NumberPath<Double> pathAV_totalAdAmount = Expressions.numberPath(Double.class, pathAV, "totaladamount");

            BooleanExpression subFilter = Expressions.asNumber(1).eq(constant).and(AV.howtoSell.eq(2)).and(AV.active.eq(true));
            JPASQLQuery queryAV = new JPASQLQuery(entityManager,mssqlServer2012Templates);

            if (wholeSalerID != null) {
                subFilter = subFilter.and(AV.wholeSalerID.eq(wholeSalerID));
            }

            if (adFrom != null) {
                subFilter = subFilter.and(AV.fromDate.goe(adFromTimestamp));
            }

            if (adTo != null) {
                subFilter = subFilter.and(AV.toDate.loe(adToTimestamp));
            }

            queryAV.select(AV.wholeSalerID,AV.actualPrice.sum().divide(AV.adID.count()).as("totaladamount")).from(AV).where(subFilter).groupBy(AV.wholeSalerID);
            jpasqlQuery.innerJoin(queryAV,pathAV).on(W.wholeSalerID.eq(AV.wholeSalerID));

            if (adSpentAmountFrom != null) {
                filter = filter.and(pathAV_totalAdAmount.goe(adSpentAmountFrom));
            }

            if (adSpentAmountTo != null) {
                filter = filter.and(pathAV_totalAdAmount.loe(adSpentAmountTo));
            }
        }

        if (userID != null) {
            if (userIDPartialMatch) {
                filter = filter.and(W.userId.contains(userID));
            } else {
                filter = filter.and(W.userId.eq(userID));
            }
        }

        if (companyName != null) {
            if (companyNamePartialMatch) {
                filter = filter.and(W.companyName.contains(companyName));
            } else {
                filter = filter.and(W.companyName.eq(companyName));
            }
        }

        if (firstName != null) {
            if (firstNamePartialMatch) {
                filter = filter.and(W.firstName.contains(firstName));
            } else {
                filter = filter.and(W.firstName.eq(firstName));
            }
        }

        if (lastName != null) {
            if (lastNamePartialMatch) {
                filter = filter.and(W.lastName.contains(lastName));
            } else {
                filter = filter.and(W.lastName.eq(lastName));
            }
        }

        if (assignedUser > 0) {
            filter = filter.and(VC.repID.eq(assignedUser));
        }

        if (createdOnFrom != null) { filter = filter.and(W.startingDate.goe(Timestamp.valueOf(createdOnFrom))); }
        if (createOnTo != null) { filter = filter.and(W.startingDate.loe(Timestamp.valueOf(createOnTo))); }
        if (active != null) { filter = filter.and(W.active.eq(active)); }
        if (shopActive != null) { filter = filter.and(W.shopActive.eq(shopActive)); }
        if (orderActive != null) { filter = filter.and(W.orderActive.eq(orderActive)); }
        if (oldCompanyName != null) { filter = filter.and(V.nameHistory.contains(oldCompanyName)); }
        if (wholeSalerID != null) { filter = filter.and(W.wholeSalerID.eq(wholeSalerID)); }
        if (StringUtils.isNotEmpty(companyType)) { filter = filter.and(W.companyTypeID.in(companyTypeArray)); }
        if (actualOpenFrom != null) { filter = filter.and(W.actualOpenDate.goe(actualOpenFromTimestamp)); }
        if (actualOpenTo != null) { filter = filter.and(W.actualOpenDate.loe(actualOpenToTimestamp)); }
        if (contractExpiredateFrom != null) { filter = filter.and(W.contractExpireDate.goe(contractExpiredateFromTimestamp)); }
        if (contractExpiredateTo != null) { filter = filter.and(W.contractExpireDate.loe(contractExpiredateToTimestamp)); }

        if (categoryModel != 0) {
            filter = filter.and(W.wholeSalerID.in(SQLExpressions.select(C.entityID).from(C).where(C.countTypeID.eq(2).and(C.referenceID.eq(categoryModel)))));
        }

        if (location != null) {
            if (location.equals("229") || location.equals("38")) {
                filter = filter.and(W.billCountryID.eq(Integer.valueOf(location)));
            } else {
                filter = filter.and(queryDSLSQLFunctions.isnull(Integer.class, W.billCountryID, 0).ne(229).and(queryDSLSQLFunctions.isnull(Integer.class, W.billCountryID, 0).ne(38)));

                if (!country.equals("ALL")) {
                    filter = filter.and(queryDSLSQLFunctions.isnull(Integer.class, W.billCountryID, 0).eq(Integer.valueOf(country)));
                }
            }

            if (!state.equals("ALL")) {
                filter = filter.and(W.billState.eq(state));
            }
        }

        if (!typeOfContract.equals("0")) { filter = filter.and(VC.contractTypeID.eq(Integer.valueOf(typeOfContract))); }

        if (!photoplan.equals("0")) {
            filter = filter.and(VC.photoPlanID.eq(Integer.valueOf(photoplan)));
            if (photoplan.equals("1")){
                if (!chooseType.equals("0")) {
                    // mannequin : 1 -> true
                    // model : 2 -> false
                    boolean chooseTypeBoolean = chooseType.equalsIgnoreCase("1");

                    filter = filter.and(VC.useModel.eq(chooseTypeBoolean));
                }
            }
        }

        if (!commission.equals("0")) {
            if (commission.equals("1")) {
                filter = filter.and(VC.commissionRate.gt(0));
            } else {
                filter = filter.and(VC.commissionRate.eq(BigDecimal.valueOf(0)));
            }
        }

        switch (status) {
            case 1:
                filter = filter.and(W.orderActive.eq(true));
                break;
            case 2:
                filter = filter.and(W.shopActive.eq(true).and(W.orderActive.eq(false)));
                break;
            case 3:
                filter = filter.and(W.active.eq(true).and(W.shopActive.eq(false).and(W.orderActive.eq(false))));
                break;
            case 4:
                filter = filter.and(W.wholeSalerID.in(SQLExpressions.select(VB.wholeSalerId).from(VB)));
                break;
            case 5:
                filter = filter.and(W.wholeSalerID.in(SQLExpressions.select(LVH.wholeSalerID).from(LVH).where(LVH.active.eq(true).and(LVH.holdFrom.loe(nowTimestamp).and(LVH.holdTo.goe(nowTimestamp))))));
                break;
            case 6:
                filter = filter.and(W.contractExpireDate.isNotNull());
                break;
            case 7:
                filter = filter.and(W.active.eq(false));
                break;
            case 8:
                filter = filter.and(W.actualOpen.eq("Y"));
                break;
        }

        if (fgExclusiveType != null) {
            filter = filter.and(W.fashionGoExclusive.eq(true));
        }

        if (sourcetype > 0) {
            filter = filter.and(W.sourceType.eq(sourcetype));
        }

        jpasqlQuery.where(filter).offset(offset).limit(limit).orderBy(W.startingDate.desc());

        QueryResults<VendorList> vendorListQueryResults = jpasqlQuery.fetchResults();
		long total = vendorListQueryResults.getTotal();
		List<VendorList> results = vendorListQueryResults.getResults();
		PageRequest pageRequest = PageRequest.of( pageNum - 1, pageSize);
		return PageableExecutionUtils.getPage(results, pageRequest, () -> total);
    }

    @Override
    public List<VendorListCSV> getVendorListCSVWithCount(GetVendorListParameter param) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");

        Integer pageNum = param.getPageNum() == null ? 0 : param.getPageNum();
        Integer pageSize = param.getPageSize() == null ? 0 : param.getPageSize();
        String userID = StringUtils.isEmpty(param.getUserID()) ? null : param.getUserID();
        Boolean userIDPartialMatch = param.getUserIdPartialMatch();
        String companyName = StringUtils.isEmpty(param.getCompanyName()) ? null : param.getCompanyName();
        Boolean companyNamePartialMatch = true;
        Boolean companyNameStartsWith = null;
        String firstName = null;
        Boolean firstNamePartialMatch = null;
        String lastName = null;
        Boolean lastNamePartialMatch = null;
        LocalDateTime createdOnFrom = null;
        LocalDateTime createOnTo = null;
        Boolean active = null;
        Boolean shopActive = null;
        Boolean orderActive = null;
        String orderBy = StringUtils.isEmpty(param.getOrderBy()) ? null : param.getOrderBy();
        String oldCompanyName = StringUtils.isEmpty(param.getOldCompanyName()) ? null : param.getOldCompanyName();
        Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
        String companyType = StringUtils.isEmpty(param.getCompanyType()) ? "" : param.getCompanyType();

        List<Integer> companyTypeArray = new ArrayList<>();
        if (StringUtils.isNotEmpty(companyType)) {
            for (String type : companyType.split(",")) {
                if (StringUtils.isNotEmpty(type))
                    companyTypeArray.add(Integer.valueOf(type));
            }
        }

        String location = StringUtils.isEmpty(param.getLocation()) ? null : param.getLocation();
        String state = StringUtils.isEmpty(param.getState()) ? null : param.getState();
        String country = StringUtils.isEmpty(param.getCountry()) ? null : param.getCountry();
        String typeOfContract = StringUtils.isEmpty(param.getTypeOfContract()) ? null : param.getTypeOfContract();
        String photoplan = StringUtils.isEmpty(param.getPhotoPlan()) ? null : param.getPhotoPlan();
        String chooseType = StringUtils.isEmpty(param.getChooseType()) ? null : param.getChooseType();
        String commission = StringUtils.isEmpty(param.getCommission()) ? null : param.getCommission();


        String actualOpenFrom = StringUtils.isEmpty(param.getActualopenfrom()) ? null : param.getActualopenfrom();
        String actualOpenTo = StringUtils.isEmpty(param.getActualopento()) ? null : param.getActualopento();

        Timestamp actualOpenFromTimestamp = null;
        if (StringUtils.isNotEmpty(actualOpenFrom)) {
            actualOpenFromTimestamp = Timestamp.valueOf(LocalDateTime.parse(actualOpenFrom, formatter));
        }
        Timestamp actualOpenToTimestamp = null;
        if (StringUtils.isNotEmpty(actualOpenTo)) {
            actualOpenToTimestamp = Timestamp.valueOf(LocalDateTime.parse(actualOpenTo, formatter));
        }

        BigDecimal avgOrderAmountFrom = param.getAvgorderamountfrom();
        BigDecimal avgOrderAmountTo = param.getAvgorderamountto();


        String checkoutFrom = StringUtils.isEmpty(param.getCheckoutfrom()) ? null : param.getCheckoutfrom();
        String checkoutTo = StringUtils.isEmpty(param.getCheckoutto()) ? null : param.getCheckoutto();

        Timestamp checkoutFromTimestamp = null;
        if (StringUtils.isNotEmpty(checkoutFrom)) {
            checkoutFromTimestamp = Timestamp.valueOf(LocalDateTime.parse(checkoutFrom, formatter));
        }
        Timestamp checkoutToTimestamp = null;
        if (StringUtils.isNotEmpty(checkoutTo)) {
            checkoutToTimestamp = Timestamp.valueOf(LocalDateTime.parse(checkoutTo, formatter));
        }

        BigDecimal adSpentAmountFrom = param.getAdspentamountfrom();
        BigDecimal adSpentAmountTo = param.getAdspentamountto();


        String adFrom = StringUtils.isEmpty(param.getAdfrom()) ? null : param.getAdfrom();
        String adTo = StringUtils.isEmpty(param.getAdto()) ? null : param.getAdto();

        Timestamp adFromTimestamp = null;
        if (StringUtils.isNotEmpty(adFrom)) {
            adFromTimestamp = Timestamp.valueOf(LocalDateTime.parse(adFrom, formatter));
        }
        Timestamp adToTimestamp = null;
        if (StringUtils.isNotEmpty(adTo)) {
            adToTimestamp = Timestamp.valueOf(LocalDateTime.parse(adTo, formatter));
        }

        Integer recurringFrom = param.getRecurringfrom();
        Integer recurringTo = param.getRecurringto();
        Integer categoryModel = param.getCategoryModel() == null ? 0 : param.getCategoryModel();
        Integer status = param.getStatus() == null ? 0 : param.getStatus();
        Integer assignedUser = param.getAssignedUser() == null ? 0 : param.getAssignedUser();
        Integer fgExclusiveType = param.getFgExclusiveType();

        if (wholeSalerID == 0) wholeSalerID = null;

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression filter = Expressions.asNumber(1).eq(constant);

        MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
        JPASQLQuery<VendorListCSV> jpasqlQuery = new JPASQLQuery<VendorListCSV>(entityManager, mssqlServer2012Templates);

        Timestamp nowTimestamp = Timestamp.valueOf(LocalDateTime.now());

        QWholeSalerEntity T = QWholeSalerEntity.wholeSalerEntity;
        QVendorBlockedEntity VB = QVendorBlockedEntity.vendorBlockedEntity;
        QLogVendorHoldEntity LVH = QLogVendorHoldEntity.logVendorHoldEntity;
        QMapWholeSalerGroupEntity MWG = QMapWholeSalerGroupEntity.mapWholeSalerGroupEntity;
        QVendorNameHistoryEntity V = QVendorNameHistoryEntity.vendorNameHistoryEntity;
        QVendorContractEntity VC = QVendorContractEntity.vendorContractEntity;


        jpasqlQuery.select(Projections.constructor(VendorListCSV.class,
                T.wholeSalerID,
                Expressions.cases().when(T.companyTypeID.eq(1)).then("M").when(T.companyTypeID.eq(2)).then("W").when(T.companyTypeID.eq(3)).then("D").otherwise("").as("Type"),
                T.companyName.as("Comapny Name"),
                queryDSLSQLFunctions.isnull(String.class, V.nameHistory, "").as("Old Company Name"),
                queryDSLSQLFunctions.isnull(String.class, T.businessCategory, "").as("Vendor Category"),
                T.userId,
                T.firstName,
                T.lastName,
                T.email,
                T.billPhone.as("Show room Phone"),
                T.billStreetNo.concat(" ").concat(T.billStreetNo2).as("Show room Street"),
                T.billCity.as("Show room City"),
                T.billState.as("ShoowroomSTATE"),
                T.billZipcode.as("Show room Zipcode"),
                T.billCountry.as("ShowroomCountry"),
                T.phone.as("Factory Phone"),
                T.streetNo.concat(" ").concat(T.streetNo2).as("Factory Street"),
                T.city.as("Factory City"),
                T.state.as("FactorySTATE"),
                T.country.as("FactoryCountry"),
                T.zipcode.as("Factory Zipcode"),
                Expressions.cases().when(T.active.eq(true)).then("Y").otherwise("N").as("Active"),
                Expressions.cases().when(T.orderActive.eq(true)).then("Order Active").otherwise(
                        Expressions.cases().when(T.shopActive.eq(true)).then("Shop Active").otherwise(
                                Expressions.cases().when(T.active.eq(true)).then("Active").otherwise("Inactive")
                        )
                ).as("Current Status"),
                T.actualOpenDate.as("Order Active on"),
                T.billCountryID,
                VC.contractTypeID,
                VC.photoPlanID,
                VC.useModel,
                VC.commissionRate,
                T.fashionGoExclusive,
                SQLExpressions.select(VB.wholeSalerId.count()).from(VB).where(VB.wholeSalerId.eq(T.wholeSalerID)).as("BlockedCheck"),
                SQLExpressions.select(LVH.logID.count()).from(LVH).where(LVH.active.eq(true).and(LVH.holdFrom.loe(nowTimestamp).and(LVH.holdTo.goe(nowTimestamp)).and(LVH.wholeSalerID.eq(T.wholeSalerID)))).as("HoldCheck")
                ))
                .from(T)
                .leftJoin(V).on(T.wholeSalerID.eq(V.wholeSalerID))
                .leftJoin(VC).on(T.wholeSalerID.eq(VC.wholeSalerID).and(VC.vendorContractID.in(SQLExpressions.select(VC.vendorContractID.max()).from(VC).groupBy(VC.wholeSalerID))));

        if (assignedUser > 0) {
            QMapWaUserVendorEntity MWUV = QMapWaUserVendorEntity.mapWaUserVendorEntity;

            jpasqlQuery.innerJoin(MWUV).on(T.wholeSalerID.eq(MWUV.vendorID).and(MWUV.userID.eq(assignedUser)));
        }

        if (avgOrderAmountFrom != null || avgOrderAmountTo != null || checkoutFrom != null || checkoutTo != null) {
            SimplePath<Object> pathO = Expressions.path(Object.class, "O");
            QOrdersEntity O = new QOrdersEntity("O");
            NumberPath<Double> pathO_totalOrderAmount = Expressions.numberPath(Double.class, pathO, "totalorderamount");

            BooleanExpression subFilter = O.orderStatusID.notIn(0);
            JPASQLQuery queryOrder = new JPASQLQuery(entityManager,mssqlServer2012Templates);

            if (wholeSalerID != null) {
                subFilter = subFilter.and(O.wholeSalerID.eq(wholeSalerID));
            }

            if (checkoutFrom != null) {
                subFilter = subFilter.and(O.checkOutDate.goe(checkoutFromTimestamp));
            }

            if (checkoutTo != null) {
                subFilter = subFilter.and(O.checkOutDate.loe(checkoutToTimestamp));
            }

            queryOrder.select(O.wholeSalerID, O.totalAmountWSC.sum().divide(O.orderID.count()).as("totalorderamount")).from(O).where(subFilter).groupBy(O.wholeSalerID);
            jpasqlQuery.innerJoin(queryOrder, pathO).on(T.wholeSalerID.eq(O.wholeSalerID));

            if (avgOrderAmountFrom != null) {
                filter = filter.and(pathO_totalOrderAmount.goe(avgOrderAmountFrom));
            }

            if (avgOrderAmountTo != null) {
                filter = filter.and(pathO_totalOrderAmount.loe(avgOrderAmountTo));
            }
        }

        if (adSpentAmountFrom != null || adSpentAmountTo != null || adFrom != null || adTo != null) {
            SimplePath<Object> pathAD = Expressions.path(Object.class, "AD");
            QAdVendorEntity AD = new QAdVendorEntity("AD");
            NumberPath<Double> pathAD_totaladamount = Expressions.numberPath(Double.class, pathAD, "totaladamount");

            BooleanExpression subFilter = Expressions.asNumber(1).eq(constant).and(AD.howtoSell.eq(2)).and(AD.active.eq(true));
            JPASQLQuery queryAD = new JPASQLQuery(entityManager, mssqlServer2012Templates);

            if (wholeSalerID != null) {
                subFilter = subFilter.and(AD.wholeSalerID.eq(wholeSalerID));
            }

            if (adFrom != null) {
                subFilter = subFilter.and(AD.fromDate.goe(adFromTimestamp));
            }

            if (adTo != null) {
                subFilter = subFilter.and(AD.toDate.loe(adToTimestamp));
            }

            queryAD.select(AD.wholeSaler, AD.actualPrice.sum().divide(AD.adID.count()).as("totaladamount")).from(AD).where(subFilter).groupBy(AD.wholeSalerID);
            jpasqlQuery.innerJoin(queryAD, pathAD).on(T.wholeSalerID.eq(AD.wholeSalerID));

            if (adSpentAmountFrom != null) {
                filter = filter.and(pathAD_totaladamount.goe(adSpentAmountFrom));
            }

            if (adSpentAmountTo != null) {
                filter = filter.and(pathAD_totaladamount.loe(adSpentAmountTo));
            }
        }

        if (userID != null) {
            if (userIDPartialMatch) {
                filter = filter.and(T.userId.contains(userID));
            } else {
                filter = filter.and(T.userId.eq(userID));
            }
        }

        if (companyName != null) {
            if (companyNamePartialMatch) {
                filter = filter.and(T.companyName.contains(companyName));
            } else {
                filter = filter.and(T.companyName.eq(companyName));
            }
        }

        if (firstName != null) {
            if (firstNamePartialMatch) {
                filter = filter.and(T.firstName.contains(firstName));
            } else {
                filter = filter.and(T.firstName.eq(firstName));
            }
        }

        if (lastName != null) {
            if (lastNamePartialMatch) {
                filter = filter.and(T.lastName.contains(lastName));
            } else {
                filter = filter.and(T.lastName.eq(lastName));
            }
        }

        if (createdOnFrom != null) { filter = filter.and(T.startingDate.goe(Timestamp.valueOf(createdOnFrom))); }
        if (createOnTo != null) { filter = filter.and(T.startingDate.loe(Timestamp.valueOf(createOnTo))); }
        if (active != null) { filter = filter.and(T.active.eq(active)); }
        if (shopActive != null) { filter = filter.and(T.shopActive.eq(shopActive)); }
        if (orderActive != null) { filter = filter.and(T.orderActive.eq(orderActive)); }
        if (oldCompanyName != null) { filter = filter.and(V.nameHistory.contains(oldCompanyName)); }
        if (wholeSalerID != null) { filter = filter.and(T.wholeSalerID.eq(wholeSalerID)); }
        if (!companyType.equals("")) { filter = filter.and(T.companyTypeID.in(companyTypeArray)); }
        if (actualOpenFrom != null) { filter = filter.and(T.actualOpenDate.goe(actualOpenFromTimestamp)); }
        if (actualOpenTo != null) { filter = filter.and(T.actualOpenDate.loe(actualOpenToTimestamp)); }

        if (categoryModel != 0) {
            QCountEntity COUNT = QCountEntity.countEntity;

            filter = filter.and(T.wholeSalerID.in(SQLExpressions.select(COUNT.entityID).from(COUNT).where(COUNT.countTypeID.eq(2).and(COUNT.referenceID.eq(categoryModel)))));
        }

        if (location != null) {
            if (location.equals("229") || location.equals("38")) {
                filter = filter.and(T.billCountryID.eq(Integer.valueOf(location)));
            } else {
                filter = filter.and(queryDSLSQLFunctions.isnull(Integer.class, T.billCountryID, 0).ne(229).and(queryDSLSQLFunctions.isnull(Integer.class, T.billCountryID, 0).ne(38)));

                if (!country.equals("ALL")) {
                    filter = filter.and(queryDSLSQLFunctions.isnull(Integer.class, T.billCountryID, 0).eq(Integer.valueOf(country)));
                }
            }

            if (!state.equals("ALL")) {
                filter = filter.and(T.billState.eq(state));
            }
        }

        if (!typeOfContract.equals("0")) {
            filter = filter.and(VC.contractTypeID.eq(Integer.valueOf(typeOfContract)));
        }

        if (!photoplan.equals("0")) {
            filter = filter.and(VC.photoPlanID.eq(Integer.valueOf(photoplan)));

            if (photoplan.equals("1")) {
                if (!chooseType.equals("0")) {
                    boolean chooseTypeBoolean = chooseType.equalsIgnoreCase("1");

                    filter = filter.and(VC.useModel.eq(chooseTypeBoolean));
                }
            }
        }

        if (!commission.equals("0")) {
            if (commission.equals("1")) {
                filter = filter.and(VC.commissionRate.gt(0));
            } else {
                filter = filter.and(VC.commissionRate.eq(BigDecimal.valueOf(0)));
            }
        }

        switch (status) {
            case 1:
                filter = filter.and(T.orderActive.eq(true));
                break;
            case 2:
                filter = filter.and(T.shopActive.eq(true).and(T.orderActive.eq(false)));
                break;
            case 3:
                filter = filter.and(T.active.eq(true).and(T.shopActive.eq(false)).and(T.orderActive.eq(false)));
                break;
            case 4:
                filter = filter.and(T.wholeSalerID.in(SQLExpressions.select(VB.wholeSalerId).from(VB)));
                break;
            case 5:
                filter = filter.and(T.wholeSalerID.in(SQLExpressions.select(LVH.wholeSalerID).from(LVH).where(LVH.active.eq(true).and(LVH.holdFrom.loe(nowTimestamp).and(LVH.holdTo.goe(nowTimestamp))))));
                break;
            case 6:
                filter = filter.and(T.contractExpireDate.isNotNull());
                break;
            case 7:
                filter = filter.and(T.active.eq(false));
                break;
            case 8:
                filter = filter.and(T.actualOpen.eq("Y"));
                break;
        }

        if (fgExclusiveType != null) {
            filter = filter.and(T.fashionGoExclusive.eq(true));
        }

        jpasqlQuery.where(filter).orderBy(T.wholeSalerID.desc(), T.startingDate.desc());

        return jpasqlQuery.fetch();
    }


    private BooleanExpression eqReferenceID(Integer referenceID, QCategoryEntity C) {
        if (referenceID == null) {
            return null;
        }
        return C.categoryId.eq(referenceID);
    }
}
