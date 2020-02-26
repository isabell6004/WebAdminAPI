package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.ProjectableSQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.message.VendorRating;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WholeSalerRatingEntityRepositoryCustomImpl implements WholeSalerRatingEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public Page<VendorRating> up_wa_GetVendorInfoRating(
            Integer retailerId, Integer wholesalerId, int pageNum, int pageSize,
            Boolean active, LocalDateTime from, LocalDateTime to, String orderBy,
            Integer score, String retailerCompanyName, String wholesalerCompanyName) {

        long offset = (pageNum - 1) * pageSize;
        long limit = pageSize;

        MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
        JPASQLQuery<VendorRating> jpasqlQuery = new JPASQLQuery<>(entityManager, mssqlServer2012Templates);
        JPASQLQuery subQuery = new JPASQLQuery(entityManager, mssqlServer2012Templates);
        QWholeSalerRatingEntity RR = QWholeSalerRatingEntity.wholeSalerRatingEntity;
        QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;
        QOrdersEntity ORDER = QOrdersEntity.ordersEntity;
        QRetailerEntity R = QRetailerEntity.retailerEntity;
        QRatingCommentEntity RC = QRatingCommentEntity.ratingCommentEntity;

        SimplePath<Object> vwWholeSalerRatingDetail = Expressions.path(Object.class, "vwWholeSalerRatingDetail");
        NumberPath<Integer> path_wholeSalerRatingID = Expressions.numberPath(Integer.class, vwWholeSalerRatingDetail, "WholeSalerRatingID");
        NumberPath<Integer> path_orderID = Expressions.numberPath(Integer.class, vwWholeSalerRatingDetail, "OrderID");
        NumberPath<Integer> path_wholeSalerID = Expressions.numberPath(Integer.class, vwWholeSalerRatingDetail, "WholeSalerID");
        NumberPath<Integer> path_retailerid = Expressions.numberPath(Integer.class, vwWholeSalerRatingDetail, "RetailerID");
        NumberPath<Integer> path_score = Expressions.numberPath(Integer.class, vwWholeSalerRatingDetail, "Score");
        StringPath path_comment = Expressions.stringPath(vwWholeSalerRatingDetail, "Comment");
        StringPath path_wholeSalerCompanyName = Expressions.stringPath(vwWholeSalerRatingDetail, "WholeSalerCompanyName");
        StringPath path_retailerCompanyName = Expressions.stringPath(vwWholeSalerRatingDetail, "RetailerCompanyName");
        StringPath path_retailerFullName = Expressions.stringPath(vwWholeSalerRatingDetail, "RetailerFullName");
        StringPath path_poNumber = Expressions.stringPath(vwWholeSalerRatingDetail, "PONumber");
        StringPath path_response = Expressions.stringPath(vwWholeSalerRatingDetail, "Response");
        BooleanPath path_active = Expressions.booleanPath(vwWholeSalerRatingDetail, "Active");
        DateTimePath<Timestamp> path_createdOn = Expressions.dateTimePath(Timestamp.class, vwWholeSalerRatingDetail, "CreatedOn");

        BooleanExpression expression = Expressions.asNumber(1).eq(1);

        if (retailerId != null) {
            expression = expression.and(path_retailerid.eq(retailerId));
        }

        if (wholesalerId != null) {
            expression = expression.and(path_wholeSalerID.eq(wholesalerId));
        }

        if (active != null) {
            expression = expression.and(path_active.eq(active));
        }

        if (score != null) {
            expression = expression.and((path_score.eq(score)));
        }

        if (StringUtils.isNotEmpty(retailerCompanyName)) {
            expression = expression.and(path_retailerCompanyName.likeIgnoreCase('%' + retailerCompanyName + '%'));
        }

        if (StringUtils.isNotEmpty(wholesalerCompanyName)) {
            expression = expression.and(path_wholeSalerCompanyName.likeIgnoreCase('%' + wholesalerCompanyName + '%'));
        }

        if (from != null) {
            expression = expression.and(path_createdOn.goe(Timestamp.valueOf(from)));
        }

        if (to != null) {
            expression = expression.and(path_createdOn.loe(Timestamp.valueOf(to)));
        }

        List<OrderSpecifier> orderSpecifierList = new ArrayList<>();
        orderSpecifierList.add(path_createdOn.desc());
        orderSpecifierList.add(path_wholeSalerCompanyName.asc());
        if (orderBy != null) {
            orderSpecifierList.clear();
            String[] s = orderBy.split(" ");

            BooleanPath booleanPath = Expressions.booleanPath(s[0]);
            if (s[1].equalsIgnoreCase("asc")) {
                orderSpecifierList.add(booleanPath.asc());
            } else {
                orderSpecifierList.add(booleanPath.desc());
            }
        }

        ProjectableSQLQuery sqlQuery = subQuery.select(
                RR.wholeSalerRatingID.as("WholeSalerRatingID")
                , RR.orderID.as("OrderID")
                , RR.wholeSalerID.as("WholeSalerID")
                , RR.retailerID.as("RetailerID")
                , RR.comment.as("Comment")
                , RR.active.as("Active")
                , RR.createdOn.as("CreatedOn")
                , RR.score.as("Score")
                , W.companyName.as("WholeSalerCompanyName")
                , R.companyName.as("RetailerCompanyName")
                , R.firstName.concat(" ").concat(R.lastName).as("RetailerFullName")
                , ORDER.poNumber.as("PONumber")
                , RC.comment.as("Response")
        )
                .from(RR)
                .leftJoin(W).on(RR.wholeSalerID.eq(W.wholeSalerId))
                .leftJoin(ORDER).on(RR.orderID.eq(ORDER.orderID))
                .leftJoin(R).on(RR.retailerID.eq(R.retailerID))
                .leftJoin(RC).on(RR.wholeSalerID.eq(RC.referenceID).and(RC.isVendorRating.eq(true)));

        OrderSpecifier[] orderSpecifiers = orderSpecifierList.toArray(new OrderSpecifier[orderSpecifierList.size()]);
        jpasqlQuery
                .select(
                        Projections.constructor(
                                VendorRating.class
                                , SQLExpressions.rowNumber().over().orderBy(orderSpecifiers).as("rowno")
                                , path_poNumber
                                , path_wholeSalerCompanyName
                                , path_retailerCompanyName
                                , path_retailerFullName
                                , path_response
                                , path_wholeSalerRatingID
                                , path_orderID
                                , path_wholeSalerID
                                , path_retailerid
                                , path_comment
                                , path_score
                                , path_active
                                , path_createdOn
                        )
                )
                .from(sqlQuery, vwWholeSalerRatingDetail)
                .where(
                        expression
                )
                .offset(offset)
                .limit(limit)
                .orderBy(orderSpecifiers)
        ;

        QueryResults<VendorRating> retailerRatingQueryResults = jpasqlQuery.fetchResults();
        List<VendorRating> results = retailerRatingQueryResults.getResults();
        long resultsTotal = retailerRatingQueryResults.getTotal();

        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return PageableExecutionUtils.getPage(results, pageRequest, () -> resultsTotal);
    }
}
