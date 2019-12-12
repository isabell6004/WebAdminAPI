package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorAdminLoginLogEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorAdminLoginLogEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
public class VendorAdminLoginLogEntityRepositoryImpl implements VendorAdminLoginLogEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public Page<VendorAdminLoginLogEntity> findVendorAdminLoginLog(Integer pageNum, Integer pageSize, Integer wholeSalerID, String userID, LocalDate date, String ipAddress, String orderBy) {
        QVendorAdminLoginLogEntity VALL = QVendorAdminLoginLogEntity.vendorAdminLoginLogEntity;

        JPAQuery<VendorAdminLoginLogEntity> query = new JPAQuery<>(entityManager);

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression expression = Expressions.asNumber(1).eq(constant);

        long offset = (pageNum - 1) * pageSize;
        long limit = pageSize;

        if(StringUtils.isNotEmpty(userID)) {
            expression = expression.and(VALL.userName.like('%' + userID + '%'));
        }
        if (date != null) {
            expression = expression.and(VALL.loginedOn.month().eq(date.getMonth().getValue()));
            expression = expression.and(VALL.loginedOn.year().eq(date.getYear()));
            expression = expression.and(VALL.loginedOn.dayOfMonth().eq(date.getDayOfMonth()));
        }
        if (StringUtils.isNotEmpty(ipAddress)) {
            expression = expression.and(VALL.ipAddress.eq(ipAddress));
        }
        expression = expression.and(VALL.wholeSalerID.eq(wholeSalerID));

        query.select(VALL)
                .from(VALL)
                .where(expression)
                .orderBy(VALL.vendorAdminLoginID.desc())
                .offset(offset)
                .limit(limit);

        QueryResults<VendorAdminLoginLogEntity> queryResults = query.fetchResults();
        List<VendorAdminLoginLogEntity> results = queryResults.getResults();
        long resultsTotal = queryResults.getTotal();

        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return PageableExecutionUtils.getPage(results, pageRequest,()-> resultsTotal);
    }
}
