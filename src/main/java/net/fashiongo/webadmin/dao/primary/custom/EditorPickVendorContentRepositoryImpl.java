package net.fashiongo.webadmin.dao.primary.custom;

import static net.fashiongo.webadmin.model.primary.QEditorPickVendorContent.editorPickVendorContent;
import static net.fashiongo.webadmin.model.primary.QVendor.vendor;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;

import io.netty.util.internal.StringUtil;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.SingleValueResult;
import net.fashiongo.webadmin.model.primary.EditorPickVendorContent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-01
 */
public class EditorPickVendorContentRepositoryImpl extends QuerydslRepositorySupport implements EditorPickVendorContentRepositoryCustom {

    public EditorPickVendorContentRepositoryImpl() {
        super(EditorPickVendorContent.class);
    }
	
    @Override
    @PersistenceContext(unitName = "primaryEntityManager")
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
	public PagedResult<EditorPickVendorContent> getEditorPickVendorContents(Integer pagenum, Integer pagesize,
			String title, String vendorName, LocalDateTime startDate, LocalDateTime endDate, String orderBy) {
		QueryResults<EditorPickVendorContent> list = from(editorPickVendorContent)
				.innerJoin(editorPickVendorContent.vendor, vendor).fetchJoin()
				.where(!StringUtil.isNullOrEmpty(title) ? editorPickVendorContent.editorTitle.likeIgnoreCase(Expressions.asString("%").concat(title).concat("%")) : null,
						!StringUtil.isNullOrEmpty(vendorName) ? editorPickVendorContent.vendor.companyName.likeIgnoreCase(Expressions.asString("%").concat(vendorName).concat("%")) : null,
						startDate!=null ? editorPickVendorContent.startDate.goe(startDate) : null,
						endDate!=null ? editorPickVendorContent.startDate.loe(endDate) : null)
				.orderBy(getOrderBy(orderBy))
				.offset(pagesize!=null && pagenum!=null ? pagesize*(pagenum-1) : null)
				.limit(pagesize)
				.fetchResults();
		
		PagedResult<EditorPickVendorContent> result = new PagedResult<>();
        SingleValueResult total = new SingleValueResult();
        total.setTotalCount((int)list.getTotal());
        result.setTotal(total);
        result.setRecords(list.getResults()==null ? new ArrayList<EditorPickVendorContent>() : list.getResults());
        return result;
    }
    
    private OrderSpecifier<?> getOrderBy(String orderBy) {
        if(orderBy!=null) {
        	if(orderBy.equals("vendorAsc")) return editorPickVendorContent.vendor.companyName.asc();
        	else if(orderBy.equals("vendorDesc")) return editorPickVendorContent.vendor.companyName.desc();
        	else if(orderBy.equals("titleAsc")) return editorPickVendorContent.editorTitle.asc();
        	else if(orderBy.equals("titleDesc")) return editorPickVendorContent.editorTitle.desc();
        	else if(orderBy.equals("startDateAsc")) return editorPickVendorContent.startDate.asc();
        	else if(orderBy.equals("startDateDesc")) return editorPickVendorContent.startDate.desc();
        	else if(orderBy.equals("endDateAsc")) return editorPickVendorContent.endDate.asc();
        	else if(orderBy.equals("endDateDesc")) return editorPickVendorContent.endDate.desc();
        	else if(orderBy.equals("createdByAsc")) return editorPickVendorContent.createdBy.asc();
        	else if(orderBy.equals("createdByDesc")) return editorPickVendorContent.createdBy.desc();
        }
        return null;
    }
}
