package net.fashiongo.webadmin.dao.primary.custom;

import static net.fashiongo.webadmin.model.primary.QVendorContent.vendorContent;
import static net.fashiongo.webadmin.model.primary.QVendorContentFile.vendorContentFile;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.model.primary.VendorContentFile;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.Expressions;

import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.SingleValueResult;
import net.fashiongo.webadmin.model.primary.Vendor;
import net.fashiongo.webadmin.model.primary.VendorContent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-13
 */
public class VendorContentRepositoryImpl extends QuerydslRepositorySupport implements VendorContentRepositoryCustom {

    public VendorContentRepositoryImpl() {
        super(Vendor.class);
    }
	
    @Override
    @PersistenceContext(unitName = "primaryEntityManager")
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
	public PagedResult<VendorContent> getVendorContents(Integer pagenum, Integer pagesize, String company,Integer contentfileid, LocalDateTime datefrom, LocalDateTime dateto, Integer type, Integer status) {

        Integer vendorcontentid = null;
        if (contentfileid!=null) {
            VendorContentFile contentfile = null;
            contentfile =
                    from(vendorContentFile)
                    .where(vendorContentFile.vendorContentFileId.eq(contentfileid))
                    .fetchOne();
            if (contentfile != null) {
                vendorcontentid = contentfile.getVendorContentId();
            }
        }

        QueryResults<VendorContent> results =
				from(vendorContent)
				.where(vendorContent.isDeleted.eq(false),
						company!=null ? vendorContent.vendor.companyName.likeIgnoreCase(Expressions.asString("%").concat(company).concat("%")) : null,
                        vendorcontentid!=null ? vendorContent.vendorContentId.eq(vendorcontentid) : null,
						datefrom!=null ? vendorContent.requestedOn.goe(datefrom) : null,
						dateto!=null ? vendorContent.requestedOn.loe(dateto) : null,
						type!=null ? vendorContent.targetTypeId.eq(type) : null,
						status!=null ? vendorContent.statusId.eq(status) : null)
				.offset(pagesize!=null && pagenum!=null ? pagesize*((long)pagenum-1) : 0)
				.limit(pagesize!=null ? pagesize : 100)
				.fetchResults();
		
		PagedResult<VendorContent> pagedResults = new PagedResult<>();
		SingleValueResult total = new SingleValueResult();
        total.setTotalCount((int)results.getTotal());
        pagedResults.setTotal(total);
        pagedResults.setRecords(results.getResults());
        return pagedResults;
    }
}