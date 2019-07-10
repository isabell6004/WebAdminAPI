package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLServer2012Templates;
import net.fashiongo.webadmin.data.entity.primary.QListApplicationEntity;
import net.fashiongo.webadmin.data.entity.primary.QSecurityMenuEntity;
import net.fashiongo.webadmin.data.entity.primary.QSecurityPermissionGroupEntity;
import net.fashiongo.webadmin.data.entity.primary.QSecurityResorceEntity;
import net.fashiongo.webadmin.data.model.admin.SecurityGroupPermissions;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SecurityGroupProcedureRepositoryImpl implements SecurityGroupProcedureRepository {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Autowired
	private QueryDSLSQLFunctions queryDSLSQLFunctions;

	@Override
	public List<SecurityGroupPermissions> up_wa_Security_GetPermissionGroup(int applicationId, int groupId) {

		SQLServer2012Templates sqlServer2012Templates = new SQLServer2012Templates();
		JPASQLQuery<SecurityGroupPermissions> jpasqlQuery = new JPASQLQuery<SecurityGroupPermissions>(entityManager, sqlServer2012Templates);

		QSecurityMenuEntity M1 = new QSecurityMenuEntity("M1");
		QSecurityMenuEntity M2 = new QSecurityMenuEntity("M2");
		Path<Object> M = ExpressionUtils.path(Object.class, "M");
		NumberPath<Integer> M_ResourceID = Expressions.numberPath(Integer.class, M, "ResourceID");
		NumberPath<Integer> M_ApplicationID = Expressions.numberPath(Integer.class, M, "ApplicationID");
		NumberPath<Integer> M_MenuID = Expressions.numberPath(Integer.class, M, "MenuID");
		StringPath M_Name = Expressions.stringPath(M, "Name");
		NumberPath<Integer> M_ParentMenuID = Expressions.numberPath(Integer.class, M, "ParentMenuID");
		StringPath M_ParentName = Expressions.stringPath(M, "ParentName");
		BooleanPath M_Visible = Expressions.booleanPath(M, "Visible");

		QSecurityResorceEntity R1 = new QSecurityResorceEntity("R1");
		QSecurityResorceEntity R2 = new QSecurityResorceEntity("R2");
		Path<Object> R = ExpressionUtils.path(Object.class, "R");
		NumberPath<Integer> R_ResourceID = Expressions.numberPath(Integer.class, R, "ResourceID");
		NumberPath<Integer> R_ApplicationID = Expressions.numberPath(Integer.class, R, "ApplicationID");
		StringPath R_ResourceType = Expressions.stringPath(R, "ResourceType");
		StringPath R_Name = Expressions.stringPath(R, "Name");
		StringPath R_ParentName = Expressions.stringPath(R, "ParentName");

		QSecurityPermissionGroupEntity SPG = new QSecurityPermissionGroupEntity("SPG");
		Path<Object> GP = ExpressionUtils.path(Object.class, "GP");
		BooleanPath GP_Allow = Expressions.booleanPath(GP, "Allow");
		BooleanPath GP_AllowEdit = Expressions.booleanPath(GP, "AllowEdit");
		BooleanPath GP_AllowDelete = Expressions.booleanPath(GP, "AllowDelete");
		BooleanPath GP_AllowAdd = Expressions.booleanPath(GP, "AllowAdd");

		NumberPath<Integer> GP_ResourceID = Expressions.numberPath(Integer.class, GP, "ResourceID");
		NumberExpression<Integer> GROUP_ID = Expressions.asNumber(groupId);
		QListApplicationEntity A = new QListApplicationEntity("A");

		jpasqlQuery
				.select(
						Projections.constructor(
								SecurityGroupPermissions.class
								, M_MenuID
								, queryDSLSQLFunctions.isnull(Integer.class,R_ResourceID, 0)
								, queryDSLSQLFunctions.isnull(String.class,M_Name, R_Name)
								, M_ParentMenuID
								, queryDSLSQLFunctions.isnull(String.class,M_ParentName, R_ParentName)
								, R_ResourceType
								, queryDSLSQLFunctions.isnull(Boolean.class,GP_Allow, false)
								, queryDSLSQLFunctions.isnull(Boolean.class,GP_AllowEdit, false)
								, queryDSLSQLFunctions.isnull(Boolean.class,GP_AllowDelete, false)
								, queryDSLSQLFunctions.isnull(Boolean.class,GP_AllowAdd, false)
								, M_Visible
						)
				)
				.from(
					JPAExpressions.select(
							M1.menuID.as("MenuID")
							,M1.name.as("Name")
							,M2.menuID.as("ParentMenuID")
							,M2.name.as("ParentName")
							,M1.resourceID
							,M1.active
							,M1.applicationID
							,M1.visible.as("Visible"))
							.from(M1)
							.leftJoin(M2).on(M1.parentID.eq(M2.menuID))
							.where(M1.active.eq(true)),M)
				.fullJoin(
					JPAExpressions.select(R1.name.as("Name"),R2.name.as("ParentName"),R1.resourceID,R1.resourceType,R1.active,R1.applicationID)
					.from(R1)
					.leftJoin(R2).on(R1.parentID.eq(R2.resourceID)),R).on(M_ResourceID.eq(R_ResourceID))
				.leftJoin(
					JPAExpressions.select(SPG.resourceID,SPG.allow,SPG.allowAdd,SPG.allowDelete,SPG.allowEdit)
					.from(SPG)
					.where(
							SPG.groupID.eq(groupId)
							.or(GROUP_ID.eq(Expressions.constant(0)))
					),GP).on(R_ResourceID.eq(GP_ResourceID))
				.leftJoin(A).on(
					A.applicationID.eq(
							queryDSLSQLFunctions.isnull(Integer.class,M_ApplicationID, R_ApplicationID)
					)
				)
				.where(A.applicationID.eq(applicationId).and(M_MenuID.isNotNull()));

		return jpasqlQuery.fetch()
				.stream()
				.distinct()
				.collect(Collectors.toList());
	}
}
