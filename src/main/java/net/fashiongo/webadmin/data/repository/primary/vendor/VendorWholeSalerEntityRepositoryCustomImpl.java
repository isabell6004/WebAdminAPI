package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.common.VendorsCompanyName;
import net.fashiongo.webadmin.data.model.vendor.GetVendorListParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorAddressSimpleDto;
import net.fashiongo.webadmin.data.model.vendor.VendorBasicInfo;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import net.fashiongo.webadmin.data.model.vendor.VendorGroupingSelected;
import net.fashiongo.webadmin.data.model.vendor.VendorGroupingUnselect;
import net.fashiongo.webadmin.data.model.vendor.VendorList;
import net.fashiongo.webadmin.data.model.vendor.VendorListCSVDto;
import net.fashiongo.webadmin.data.model.vendor.VendorListCSVResponse;
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
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class VendorWholeSalerEntityRepositoryCustomImpl implements VendorWholeSalerEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Autowired
    private QueryDSLSQLFunctions queryDSLSQLFunctions;

    private final Integer ONE = Integer.valueOf(1), THREE = Integer.valueOf(3);

    @Override
    public List<VendorDetailInfo> findAllByID(Integer wholeSalerID) {
        JPAQuery<VendorBasicInfo> query = new JPAQuery<>(entityManager);

        QVendorEntity vendor = QVendorEntity.vendorEntity;
        QVendorAddressEntity VA = QVendorAddressEntity.vendorAddressEntity;
        QVendorSettingEntity VS = QVendorSettingEntity.vendorSettingEntity;
        QVendorContractHistoryEntity VCH = QVendorContractHistoryEntity.vendorContractHistoryEntity;
        QVendorIndustryEntity VI = QVendorIndustryEntity.vendorIndustryEntity;

        QAspnetMembershipEntity ASPM = QAspnetMembershipEntity.aspnetMembershipEntity;
        QVendorAdminAccountEntity VAA = QVendorAdminAccountEntity.vendorAdminAccountEntity;
        QVendorLambsKeyEntity VLK = QVendorLambsKeyEntity.vendorLambsKeyEntity;

        query.select(Projections.constructor(VendorBasicInfo.class,
                    vendor,
                ExpressionUtils.as(JPAExpressions.select(ASPM.isLockedOut).from(ASPM).where(ASPM.userId.eq(vendor.guid)),"IsLockedOut"),
                ExpressionUtils.as(JPAExpressions.select(ASPM.lastLockoutDate).from(ASPM).where(ASPM.userId.eq(vendor.guid)),"LastLockoutDate"),
                ExpressionUtils.as(JPAExpressions.select(ASPM.isLockedOut.count()).from(ASPM).where(ASPM.userId.in(JPAExpressions.select(VAA.userGUID).from(VAA).where(VAA.wholeSalerID.eq(wholeSalerID).and(ASPM.isLockedOut.eq(true))))),"IsLockedOut2"),
                ExpressionUtils.as(JPAExpressions.select(VLK.wholeSalerID.count()).from(VLK).where(VLK.wholeSalerID.eq(wholeSalerID)),"elambsuser")
                )).from(vendor)
                .innerJoin(VA).on(vendor.vendor_id.eq(VA.vendorId).and(VA.typeCode.eq(1)))
                .innerJoin(VS).on(vendor.vendor_id.eq(VS.vendorId))
                .innerJoin(VCH).on(vendor.vendor_id.eq(VCH.vendorId))
                .innerJoin(VI).on(vendor.vendor_id.eq(VI.vendorId))
                .where(vendor.vendor_id.eq(wholeSalerID.longValue())).distinct();

        List<VendorBasicInfo> queryResult = query.fetch();
        List<VendorDetailInfo> result = Collections.singletonList(new VendorDetailInfo(queryResult.get(0)));
        return result;
    }

    @Override
    public WholeSalerEntity findOneByID(Integer wholeSalerID) {
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;

        JPAQuery<WholeSalerEntity> query = new JPAQuery<>(entityManager);

        query.select(W).from(W).where(W.wholeSalerID.eq(wholeSalerID));

        return query.fetchFirst();
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
        String firstName = null;
        Boolean firstNamePartialMatch = null;
        String lastName = null;
        Boolean lastNamePartialMatch = null;
        LocalDateTime createdOnFrom = null;
        LocalDateTime createOnTo = null;
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

        QVendorEntity V = new QVendorEntity("V");
        QVendorSettingEntity VS = new QVendorSettingEntity("VS");
        QLogVendorHoldEntity LVH = QLogVendorHoldEntity.logVendorHoldEntity;
        QMapWholeSalerGroupEntity MWG = QMapWholeSalerGroupEntity.mapWholeSalerGroupEntity;
        QVendorContractHistoryEntity VC = QVendorContractHistoryEntity.vendorContractHistoryEntity;
        QCountEntity C = QCountEntity.countEntity;
        QVendorNameHistoryEntity VNH = new QVendorNameHistoryEntity("VNH");
        QVendorAddressEntity VA = new QVendorAddressEntity("VA");
        MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
        JPASQLQuery<VendorList> jpasqlQuery = new JPASQLQuery<VendorList>(entityManager, mssqlServer2012Templates);

        Timestamp nowTimestamp = Timestamp.valueOf(LocalDateTime.now());

        jpasqlQuery.select(Projections.constructor(VendorList.class,
                SQLExpressions.rowNumber().over().orderBy(V.startingDate.desc()).as("rowno"),
                Expressions.stringTemplate("convert(varchar(25),{0},126)", VS.closedDate),
                V.vendor_id,
                V.name,
                V.typeCode,
                V.firstName,
                V.lastName,
                V.email,
                V.createdBy.as("userID"),
                Expressions.cases().when(VS.statusCode.in(1, 2, 3)).then(true).otherwise(false),
                Expressions.cases().when(VS.statusCode.in(2, 3)).then(true).otherwise(false),
                Expressions.cases().when(VS.statusCode.in(3)).then(true).otherwise(false),
                Expressions.stringTemplate("convert(varchar(25),{0},126)", V.startingDate),
                Expressions.stringTemplate("convert(varchar(25),{0},126)", VS.modifiedOn),
                VNH.nameHistory,
                ExpressionUtils.as(SQLExpressions.select(MWG.mapID.count()).from(MWG).where(MWG.wholeSalerID.eq(V.vendor_id.intValue())), "Grouped"),
                V.businessCategoryInfo,
                Expressions.asNumber(0).as("CheckBox"),
                Expressions.asString("").as("LinkCheck"),
                ExpressionUtils.as(SQLExpressions.select(VS.id.count()).from(VS).where(VS.vendorId.eq(V.vendor_id).and(VS.isBlock.isTrue())), "BlockedCheck"),
                ExpressionUtils.as(SQLExpressions.select(LVH.logID.count()).from(LVH).where(
                        LVH.active.eq(true).and(
                                LVH.holdFrom.loe(nowTimestamp).and(LVH.holdTo.goe(nowTimestamp)))
                                .and(LVH.wholeSalerID.eq(V.vendor_id.intValue()))), "HoldCheck"),

                VC.typeCode,
                VC.commissionRate,
                VS.isExclusive,
                V.sourceCode
        ))
                .from(V)
                .leftJoin(VA).on(V.vendor_id.eq(VA.vendorId).and(VA.typeCode.eq(1)))
                .innerJoin(VS).on(V.vendor_id.eq(VS.vendorId))
                .leftJoin(VNH).on(V.vendor_id.eq(VNH.wholeSalerID.longValue()))
                .leftJoin(VC).on(V.vendor_id.eq(VC.vendorId).and(VC.id.in(SQLExpressions.select(VC.id.max()).from(VC).groupBy(VC.vendorId))));

        if (avgOrderAmountFrom != null || avgOrderAmountTo != null || checkoutFrom != null || checkoutTo != null) {
            SimplePath<Object> pathO = Expressions.path(Object.class, "O");

            QOrdersEntity O = new QOrdersEntity("O");
            NumberPath<Double> pathO_totalOrderAmount = Expressions.numberPath(Double.class, pathO, "totalorderamount");

            BooleanExpression subFilter = O.orderStatusID.notIn(0);
            JPASQLQuery queryOrder = new JPASQLQuery(entityManager, mssqlServer2012Templates);

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
            jpasqlQuery.innerJoin(queryOrder, pathO).on(V.vendor_id.eq(O.wholeSalerID.longValue()));

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
            JPASQLQuery queryAV = new JPASQLQuery(entityManager, mssqlServer2012Templates);

            if (wholeSalerID != null) {
                subFilter = subFilter.and(AV.wholeSalerID.eq(wholeSalerID));
            }

            if (adFrom != null) {
                subFilter = subFilter.and(AV.fromDate.goe(adFromTimestamp));
            }

            if (adTo != null) {
                subFilter = subFilter.and(AV.toDate.loe(adToTimestamp));
            }

            queryAV.select(AV.wholeSalerID, AV.actualPrice.sum().divide(AV.adID.count()).as("totaladamount")).from(AV).where(subFilter).groupBy(AV.wholeSalerID);
            jpasqlQuery.innerJoin(queryAV, pathAV).on(V.vendor_id.eq(AV.wholeSalerID.longValue()));

            if (adSpentAmountFrom != null) {
                filter = filter.and(pathAV_totalAdAmount.goe(adSpentAmountFrom));
            }

            if (adSpentAmountTo != null) {
                filter = filter.and(pathAV_totalAdAmount.loe(adSpentAmountTo));
            }
        }

        if (userID != null) {
            if (userIDPartialMatch) {
                filter = filter.and(V.createdBy.contains(userID));
            } else {
                filter = filter.and(V.createdBy.eq(userID));
            }
        }

        if (companyName != null) {
            if (companyNamePartialMatch) {
                filter = filter.and(V.name.contains(companyName));
            } else {
                filter = filter.and(V.name.eq(companyName));
            }
        }

        if (firstName != null) {
            if (firstNamePartialMatch) {
                filter = filter.and(V.firstName.contains(firstName));
            } else {
                filter = filter.and(V.firstName.eq(firstName));
            }
        }

        if (lastName != null) {
            if (lastNamePartialMatch) {
                filter = filter.and(V.lastName.contains(lastName));
            } else {
                filter = filter.and(V.lastName.eq(lastName));
            }
        }

        if (assignedUser > 0) {
            filter = filter.and(VC.accountExecutiveId.eq(assignedUser));
        }

        if (createdOnFrom != null) {
            filter = filter.and(V.startingDate.goe(createdOnFrom));
        }
        if (createOnTo != null) {
            filter = filter.and(V.startingDate.loe(createOnTo));
        }
        if (oldCompanyName != null) {
            filter = filter.and(VNH.nameHistory.contains(oldCompanyName));
        }
        if (wholeSalerID != null) {
            filter = filter.and(V.vendor_id.eq(wholeSalerID.longValue()));
        }

        if (StringUtils.isNotEmpty(companyType)) {
            filter = filter.and(V.typeCode.in(companyTypeArray));
        }
        if (actualOpenFrom != null) {
            filter = filter.and(VS.openDate.goe(actualOpenFromTimestamp.toLocalDateTime()));
        }
        if (actualOpenTo != null) {
            filter = filter.and(VS.openDate.loe(actualOpenToTimestamp.toLocalDateTime()));
        }
        if (contractExpiredateFrom != null) {
            filter = filter.and(VS.closedDate.goe(contractExpiredateFromTimestamp.toLocalDateTime()));
        }
        if (contractExpiredateTo != null) {
            filter = filter.and(VS.closedDate.loe(contractExpiredateToTimestamp.toLocalDateTime()));
        }

        if (categoryModel != 0) {
            filter = filter.and(V.vendor_id.in(SQLExpressions.select(C.entityID.longValue()).from(C).where(C.countTypeID.eq(2).and(C.referenceID.eq(categoryModel)))));
        }

        if (location != null) {
            QCodeCountryEntity CCE = new QCodeCountryEntity("CCE");
            String countryName = "";
            if (location.equals("229") || location.equals("38")) {
                countryName = Integer.valueOf(location) == 229 ? "United States" : "Canada";
                filter = filter.and(VA.countryName.eq(countryName));
            } else {
                filter = filter.and(queryDSLSQLFunctions.isnull(String.class, VA.countryName, "").ne("United States").and(queryDSLSQLFunctions.isnull(String.class, VA.countryName, "").ne("Canada")));

                if (!country.equals("ALL")) {
                    countryName = new JPAQuery<>(entityManager).select(CCE.countryName).from(CCE).where(CCE.countryID.eq(Integer.valueOf(country))).fetchFirst();
                    filter = filter.and(queryDSLSQLFunctions.isnull(String.class, VA.countryName, "").eq(countryName));
                }
            }

            if (!state.equals("ALL")) {
                filter = filter.and(VA.state.eq(state));
            }
        }

        if (!typeOfContract.equals("0")) {
            filter = filter.and(VC.typeCode.eq(Integer.valueOf(typeOfContract)));
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
                filter = filter.and(VS.statusCode.eq(3));
                break;
            case 2:
                filter = filter.and(VS.statusCode.eq(2));
                break;
            case 3:
                filter = filter.and(VS.statusCode.eq(1));
                break;
            case 4:
                filter = filter.and(VS.isBlock.isTrue());
                break;
            case 5:
                filter = filter.and(V.vendor_id.in(SQLExpressions.select(LVH.wholeSalerID.longValue()).from(LVH).where(LVH.active.eq(true).and(LVH.holdFrom.loe(nowTimestamp).and(LVH.holdTo.goe(nowTimestamp))))));
                break;
            case 6:
                filter = filter.and(VS.closedDate.isNotNull());
                break;
            case 7:
                filter = filter.and(VS.statusCode.eq(0));
                break;
            case 8:
                filter = filter.and(VS.statusCode.eq(2).and(VS.openDate.after(LocalDateTime.now())));
                break;
        }

        if (fgExclusiveType != null) {
            filter = filter.and(VS.isExclusive.isTrue());
        }

        if (sourcetype > 0) {
            filter = filter.and(V.sourceCode.eq(sourcetype));
        }

        jpasqlQuery.where(filter).offset(offset).limit(limit).orderBy(V.startingDate.desc());

        QueryResults<VendorList> vendorListQueryResults = jpasqlQuery.fetchResults();
        long total = vendorListQueryResults.getTotal();
        List<VendorList> results = vendorListQueryResults.getResults();
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return PageableExecutionUtils.getPage(results, pageRequest, () -> total);
    }

    @Override
    public List<VendorListCSVResponse> getVendorListCSVWithCount(GetVendorListParameter param) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");

        String userID = StringUtils.isEmpty(param.getUserID()) ? null : param.getUserID();
        Boolean userIDPartialMatch = param.getUserIdPartialMatch();
        String companyName = StringUtils.isEmpty(param.getCompanyName()) ? null : param.getCompanyName();
        Boolean companyNamePartialMatch = true;
        String firstName = null;
        Boolean firstNamePartialMatch = null;
        String lastName = null;
        Boolean lastNamePartialMatch = null;
        LocalDateTime createdOnFrom = null;
        LocalDateTime createOnTo = null;

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


        Integer categoryModel = param.getCategoryModel() == null ? 0 : param.getCategoryModel();
        Integer status = param.getStatus() == null ? 0 : param.getStatus();
        Integer assignedUser = param.getAssignedUser() == null ? 0 : param.getAssignedUser();
        Integer fgExclusiveType = param.getFgExclusiveType();

        if (wholeSalerID == 0) wholeSalerID = null;

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression filter = Expressions.asNumber(1).eq(constant);

        MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
        JPASQLQuery<VendorListCSVDto> jpasqlQuery = new JPASQLQuery<VendorListCSVDto>(entityManager, mssqlServer2012Templates);

        Timestamp nowTimestamp = Timestamp.valueOf(LocalDateTime.now());

        QVendorEntity V = new QVendorEntity("V");
        QVendorSettingEntity VS = new QVendorSettingEntity("VS");
        QVendorAddressEntity VA = new QVendorAddressEntity("VA");
        QLogVendorHoldEntity LVH = QLogVendorHoldEntity.logVendorHoldEntity;
        QVendorNameHistoryEntity VNH = new QVendorNameHistoryEntity("VNH");
        QVendorContractHistoryEntity VC = QVendorContractHistoryEntity.vendorContractHistoryEntity;
        jpasqlQuery.select(Projections.constructor(VendorListCSVDto.class,
                V.vendor_id,
                Expressions.cases().when(V.typeCode.eq(1)).then("M").when(V.typeCode.eq(2)).then("W").when(V.typeCode.eq(3)).then("D").otherwise("").as("Type"),
                V.name,
                queryDSLSQLFunctions.isnull(String.class, VNH.nameHistory, ""),
                queryDSLSQLFunctions.isnull(String.class, V.businessCategoryInfo, ""),
                V.createdBy,
                V.firstName,
                V.lastName,
                V.email,
                Expressions.cases().when(VS.statusCode.in(1, 2, 3)).then("Y").otherwise("N").as("Active"),
                Expressions.cases().when(VS.statusCode.eq(3)).then("Order Active").otherwise(
                        Expressions.cases().when(VS.statusCode.eq(2)).then("Shop Active").otherwise(
                                Expressions.cases().when(VS.statusCode.eq(1)).then("Active").otherwise("Inactive")
                        )
                ).as("Current Status"),
                Expressions.stringTemplate("convert(varchar(25),{0},126)", VS.openDate).as("Order Active on"),
                VS.isExclusive,
                VA.phone,
                VA.streetNo1,
                VA.streetNo2,
                VA.city,
                VA.state,
                VA.zipCode,
                VA.countryName,
                VA.typeCode
        ))
                .from(V)
                .innerJoin(VS).on(V.vendor_id.eq(VS.vendorId))
                .leftJoin(VA).on(V.vendor_id.eq(VA.vendorId).and(VA.typeCode.in(1, 3)))
                .leftJoin(VNH).on(V.vendor_id.eq(VNH.wholeSalerID.longValue()))
                .leftJoin(VC).on(V.vendor_id.eq(VC.vendorId).and(VC.id.in(SQLExpressions.select(VC.id.max()).from(VC).groupBy(VC.vendorId))));

        if (assignedUser > 0) {
            QMapWaUserVendorEntity MWUV = QMapWaUserVendorEntity.mapWaUserVendorEntity;

            jpasqlQuery.innerJoin(MWUV).on(V.vendor_id.eq(MWUV.vendorID.longValue()).and(MWUV.userID.eq(assignedUser)));
        }

        if (avgOrderAmountFrom != null || avgOrderAmountTo != null || checkoutFrom != null || checkoutTo != null) {
            SimplePath<Object> pathO = Expressions.path(Object.class, "O");
            QOrdersEntity O = new QOrdersEntity("O");
            NumberPath<Double> pathO_totalOrderAmount = Expressions.numberPath(Double.class, pathO, "totalorderamount");

            BooleanExpression subFilter = O.orderStatusID.notIn(0);
            JPASQLQuery queryOrder = new JPASQLQuery(entityManager, mssqlServer2012Templates);

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
            jpasqlQuery.innerJoin(queryOrder, pathO).on(V.vendor_id.eq(O.wholeSalerID.longValue()));

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

            queryAD.select(AD.wholesaler, AD.actualPrice.sum().divide(AD.adID.count()).as("totaladamount")).from(AD).where(subFilter).groupBy(AD.wholeSalerID);
            jpasqlQuery.innerJoin(queryAD, pathAD).on(V.vendor_id.eq(AD.wholeSalerID.longValue()));

            if (adSpentAmountFrom != null) {
                filter = filter.and(pathAD_totaladamount.goe(adSpentAmountFrom));
            }

            if (adSpentAmountTo != null) {
                filter = filter.and(pathAD_totaladamount.loe(adSpentAmountTo));
            }
        }

        if (userID != null) {
            if (userIDPartialMatch) {
                filter = filter.and(V.createdBy.contains(userID));
            } else {
                filter = filter.and(V.createdBy.eq(userID));
            }
        }

        if (companyName != null) {
            if (companyNamePartialMatch) {
                filter = filter.and(V.name.contains(companyName));
            } else {
                filter = filter.and(V.name.eq(companyName));
            }
        }

        if (firstName != null) {
            if (firstNamePartialMatch) {
                filter = filter.and(V.firstName.contains(firstName));
            } else {
                filter = filter.and(V.firstName.eq(firstName));
            }
        }

        if (lastName != null) {
            if (lastNamePartialMatch) {
                filter = filter.and(V.lastName.contains(lastName));
            } else {
                filter = filter.and(V.lastName.eq(lastName));
            }
        }

        if (createdOnFrom != null) {
            filter = filter.and(V.startingDate.goe(createdOnFrom));
        }
        if (createOnTo != null) {
            filter = filter.and(V.startingDate.loe(createOnTo));
        }

        if (oldCompanyName != null) {
            filter = filter.and(VNH.nameHistory.contains(oldCompanyName));
        }
        if (wholeSalerID != null) {
            filter = filter.and(V.vendor_id.eq(wholeSalerID.longValue()));
        }
        if (!companyType.equals("")) {
            filter = filter.and(V.typeCode.in(companyTypeArray));
        }
        if (actualOpenFrom != null) {
            filter = filter.and(VS.openDate.goe(actualOpenFromTimestamp.toLocalDateTime()));
        }
        if (actualOpenTo != null) {
            filter = filter.and(VS.openDate.loe(actualOpenToTimestamp.toLocalDateTime()));
        }

        if (categoryModel != 0) {
            QCountEntity COUNT = QCountEntity.countEntity;

            filter = filter.and(V.vendor_id.in(SQLExpressions.select(COUNT.entityID.longValue()).from(COUNT).where(COUNT.countTypeID.eq(2).and(COUNT.referenceID.eq(categoryModel)))));
        }

        if (!typeOfContract.equals("0")) {
            filter = filter.and(VC.typeCode.eq(Integer.valueOf(typeOfContract)));
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
                filter = filter.and(VS.statusCode.eq(3));
                break;
            case 2:
                filter = filter.and(VS.statusCode.eq(2));
                break;
            case 3:
                filter = filter.and(VS.statusCode.eq(1));
                break;
            case 4:
                filter = filter.and(VS.isBlock.isTrue());
                break;
            case 5:
                filter = filter.and(V.vendor_id.in(SQLExpressions.select(LVH.wholeSalerID.longValue()).from(LVH).where(LVH.active.eq(true).and(LVH.holdFrom.loe(nowTimestamp).and(LVH.holdTo.goe(nowTimestamp))))));
                break;
            case 6:
                filter = filter.and(VS.closedDate.isNotNull());
                break;
            case 7:
                filter = filter.and(VS.statusCode.eq(0));
                break;
            case 8:
                filter = filter.and(VS.statusCode.eq(2).and(VS.openDate.after(LocalDateTime.now())));
                break;
        }

        if (fgExclusiveType != null) {
            filter = filter.and(VS.isExclusive.eq(true));
        }
        List<VendorListCSVDto> list = jpasqlQuery.where(filter).orderBy(V.vendor_id.desc(), V.startingDate.desc()).fetch();
        Map<Long, List<VendorListCSVDto>> vendorListMap = list.stream().collect(Collectors.groupingBy(VendorListCSVDto::getId));

        List<VendorListCSVResponse> ret = vendorListMap.keySet().stream().map(key -> {
            List<VendorListCSVDto> temp = vendorListMap.get(key);
            VendorAddressSimpleDto billAddress = temp.stream().filter(t -> ONE.equals(t.getAddressTypeCode())).findFirst().map(
                    VendorListCSVDto::createVendorAddressSimpleDto
            ).orElseGet(() -> null);
            ;
            VendorAddressSimpleDto address = temp.stream().filter(t -> THREE.equals(t.getAddressTypeCode())).findFirst().map(
                    VendorListCSVDto::createVendorAddressSimpleDto
            ).orElseGet(() -> null);
            ;
            return VendorListCSVResponse.create(temp.get(0), billAddress, address);
        }).collect(Collectors.toList());

        if (location != null) {
            QCodeCountryEntity CCE = new QCodeCountryEntity("CCE");
            if (location.equals("229") || location.equals("38")) {
                String countryName = Integer.valueOf(location) == 229 ? "United States" : "Canada";
                ret = ret.stream().filter(entity -> countryName.equals(entity.getShowRoomCountry())).collect(Collectors.toList());
            } else {
                ret = ret.stream().filter(entity -> !"United States".equals(entity.getShowRoomCountry()) && !"Canada".equals(entity.getShowRoomCountry())).collect(Collectors.toList());
                if (!country.equals("ALL")) {
                    String countryName = new JPAQuery<>(entityManager).select(CCE.countryName).from(CCE).where(CCE.countryID.eq(Integer.valueOf(country))).fetchFirst();
                    ret = ret.stream().filter(entity -> countryName.equals(entity.getShowRoomCountry())).collect(Collectors.toList());
                }
            }

            if (!"ALL".equals(state)) {
                ret = ret.stream().filter(entity -> state.equals(entity.getShowRoomState())).collect(Collectors.toList());
            }
        }
        Comparator<VendorListCSVResponse> comparator = Comparator.comparing(VendorListCSVResponse::getId).reversed();
        return ret.stream().sorted(comparator).collect(Collectors.toList());
    }


    private BooleanExpression eqReferenceID(Integer referenceID, QCategoryEntity C) {
        if (referenceID == null) {
            return null;
        }
        return C.categoryId.eq(referenceID);
    }
}
