package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QListApplicationEntity;
import net.fashiongo.webadmin.data.entity.primary.QSecurityResourceEntity;
import net.fashiongo.webadmin.data.entity.primary.SecurityResourceEntity;
import net.fashiongo.webadmin.data.model.admin.Resource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SecurityResourceEntityRepositoryCustomImpl implements SecurityResourceEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<Resource> up_wa_Security_GetResource(String application, String resourceName, String parent, String type) {

//		select r.ResourceID,r.ApplicationID, a.ApplicationName, r.Name As ResourceName, rs.Name AS ResourceParent, r.ResourceType, r.URL As ResourceUrl, r.Active
//		from dbo.[security.Resource] as r
//		left join dbo.[security.Resource] as rs on r.ParentID = rs.ResourceID
//		inner join dbo.List_Application as a on a.ApplicationID = r.ApplicationID
//		where ((@ResourceName = '') or (@ResourceName <> '' and (r.Name like @ResourceName + '%')))
//		and   ((@Parent = 'All') or (@Parent <> 'All' and rs.Name = @Parent))
//		and   ((@Type = 'All') or (@Type <> 'All' and r.ResourceType = @Type))
//		and   a.ApplicationName = @Application

		JPAQuery<SecurityResourceEntity> jpaQuery = new JPAQuery<>(entityManager);

		QSecurityResourceEntity SR = new QSecurityResourceEntity("SR");
		QSecurityResourceEntity PARENT_SR = new QSecurityResourceEntity("PARENT_SR");
		QListApplicationEntity A = new QListApplicationEntity("A");

		StringExpression RESOURCE = Expressions.asString(resourceName);
		StringExpression PARENT = Expressions.asString(parent);
		StringExpression TYPE = Expressions.asString(type);

		Expression<String> CONST_ALL = Expressions.constant("ALL");
		Expression<String> CONST_EMPTY_STRING = Expressions.constant("");

		jpaQuery.select(SR)
				.from(SR)
				.leftJoin(SR.parentResource,PARENT_SR).fetchJoin()
				.innerJoin(SR.listApplication,A).fetchJoin()
				.where(
					RESOURCE.eq(CONST_EMPTY_STRING).or(
							ExpressionUtils.and(
									RESOURCE.ne(CONST_EMPTY_STRING),SR.name.startsWith(resourceName)
							)
					).and(
							PARENT.eq(CONST_ALL).or(
									ExpressionUtils.and(
										PARENT.ne(CONST_ALL),PARENT_SR.name.eq(parent)
									)
							)
					).and(
							TYPE.eq(CONST_ALL).or(
									ExpressionUtils.and(
										TYPE.ne(CONST_ALL),SR.resourceType.eq(type)
									)
							)
					).and(
							A.applicationName.eq(application)
					)
				);

		return jpaQuery.fetch()
				.stream()
				.distinct()
				.map(securityResourceEntity -> Resource.builder()
						.active(securityResourceEntity.isActive())
						.resourceID(securityResourceEntity.getResourceID())
						.applicationID(securityResourceEntity.getApplicationID())
						.applicationName(securityResourceEntity.getListApplication().getApplicationName())
						.resourceName(securityResourceEntity.getName())
						.resourceType(securityResourceEntity.getResourceType())
						.resourceParent(securityResourceEntity.getParentResource() != null ? securityResourceEntity.getParentResource().getName() : null)
						.resourceUrl(securityResourceEntity.getURL())
						.build()
				).collect(Collectors.toList());
	}
}
