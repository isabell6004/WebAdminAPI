package net.fashiongo.webadmin.data.repository.primary.view;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.sql.JPASQLQuery;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.sitemgmt.CodeData;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.model.primary.QCodeStyle;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryViewRepositoryImpl implements CategoryViewRepository {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Autowired
	private QueryDSLSQLFunctions queryDSLSQLFunctions;

	@Override
	public Page<CodeData> findAllvwLengthCategoryByCategoryIdOrderByLengthName(Integer categoryId,int pageNumber, int pageSize) {


//		select c.LengthID, cat.CategoryID, c.LengthName, isnull(m.MapID, 0) MapID
//				from
//		Code_Length c
//		inner join Category cat on 1=1
//		left outer join Map_Length_Category m on (c.LengthID = m.LengthID and cat.CategoryID = m.CategoryID)
//		where c.Active = 1 and cat.Lvl = 2

//		DataSrc = "vwLengthCategory";
//		ColumnList = "MapID,LengthID As CodeID,LengthName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//		Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//		OrderBy = "LengthName";

		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;

		JPASQLQuery<CodeData> jpasqlQuery = new JPASQLQuery<CodeData>(entityManager,new MSSQLServer2012Templates());
		QCodeLengthEntity C = QCodeLengthEntity.codeLengthEntity;
		QCategoryEntity CAT = QCategoryEntity.categoryEntity;
		QMapLengthCategoryEntity MC = QMapLengthCategoryEntity.mapLengthCategoryEntity;
		Expression<Integer> constant = Expressions.constant(1);
		BooleanExpression expression = Expressions.asNumber(1).eq(constant);

		jpasqlQuery
				.select(
						Projections.constructor(
								CodeData.class
								, C.lengthId
								, queryDSLSQLFunctions.isnull(Integer.class,MC.mapID,0).as("MapID")
								, C.lengthName
								, Expressions.cases().when(
//										Expressions.numberPath(Integer.class,"MapID").gt(0)
										MC.mapID.gt(0)
									).then(true).otherwise(false)
								)
				)
				.from(C)
				.innerJoin(CAT).on(expression)
				.leftJoin(MC).on(C.lengthId.eq(MC.lengthID).and(CAT.categoryId.eq(MC.categoryID)))
				.where(
						C.active.eq(true).and(CAT.lvl.eq(2)).and(CAT.categoryId.eq(categoryId))
				)
				.offset(offset)
				.limit(limit)
				.orderBy(C.lengthName.asc());


		QueryResults<CodeData> codeLengthEntityQueryResults = jpasqlQuery.fetchResults();
		List<CodeData> results = codeLengthEntityQueryResults.getResults();

		long resultsTotal = codeLengthEntityQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}

	@Override
	public Page<CodeData> findAllvwStyleCategoryByCategoryIdOrderByStyleName(Integer categoryId, int pageNumber, int pageSize) {
//		Create View dbo.vwStyleCategory
//				AS
//		select c.StyleID, cat.CategoryID, c.StyleName, isnull(m.MapID, 0) MapID
//				from
//		Code_Style c
//		inner join Category cat on 1=1
//		left outer join Map_Style_Category m on (c.StyleID = m.StyleID and cat.CategoryID = m.CategoryID)
//		where c.Active = 1 and cat.Lvl = 2

//						DataSrc = "vwStyleCategory";
//						ColumnList = "MapID,StyleID As CodeID,StyleName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "StyleName";
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;

		JPASQLQuery<CodeData> jpasqlQuery = new JPASQLQuery<CodeData>(entityManager,new MSSQLServer2012Templates());
		QCodeStyle C = QCodeStyle.codeStyle;
		QCategoryEntity CAT = QCategoryEntity.categoryEntity;
		QMapStyleCategoryEntity MC = QMapStyleCategoryEntity.mapStyleCategoryEntity;
		Expression<Integer> constant = Expressions.constant(1);
		BooleanExpression expression = Expressions.asNumber(1).eq(constant);

		jpasqlQuery
				.select(
						Projections.constructor(
								CodeData.class
								, C.styleID
								, queryDSLSQLFunctions.isnull(Integer.class,MC.mapID,0).as("MapID")
								, C.styleName
								, Expressions.cases().when(
										MC.mapID.gt(0)
								).then(true).otherwise(false)
						)
				)
				.from(C)
				.innerJoin(CAT).on(expression)
				.leftJoin(MC).on(C.styleID.eq(MC.styleID).and(CAT.categoryId.eq(MC.categoryID)))
				.where(
						C.active.eq(true).and(CAT.lvl.eq(2)).and(CAT.categoryId.eq(categoryId))
				)
				.offset(offset)
				.limit(limit)
				.orderBy(C.styleName.asc());


		QueryResults<CodeData> codeLengthEntityQueryResults = jpasqlQuery.fetchResults();
		List<CodeData> results = codeLengthEntityQueryResults.getResults();

		long resultsTotal = codeLengthEntityQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}

	@Override
	public Page<CodeData> findAllvwFabricCategoryByCategoryIdOrderByfabricName(Integer categoryId, int pageNumber, int pageSize) {
//		Create View dbo.vwFabricCategory
//				AS
//		select c.FabricID, cat.CategoryID, c.FabricName, isnull(m.MapID, 0) MapID
//				from
//		Code_Fabric c
//		inner join Category cat on 1=1
//		left outer join Map_Fabric_Category m on (c.FabricID = m.FabricID and cat.CategoryID = m.CategoryID)
//		where c.Active = 1 and cat.Lvl = 2

//						DataSrc = "vwFabricCategory";
//						ColumnList = "MapID,FabricID As CodeID,FabricName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "FabricName";
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;

		JPASQLQuery<CodeData> jpasqlQuery = new JPASQLQuery<CodeData>(entityManager,new MSSQLServer2012Templates());
		QCodeFabricEntity C = QCodeFabricEntity.codeFabricEntity;
		QCategoryEntity CAT = QCategoryEntity.categoryEntity;
		QMapFabricCategoryEntity MC = QMapFabricCategoryEntity.mapFabricCategoryEntity;
		Expression<Integer> constant = Expressions.constant(1);
		BooleanExpression expression = Expressions.asNumber(1).eq(constant);

		jpasqlQuery
				.select(
						Projections.constructor(
								CodeData.class
								, C.fabricId
								, queryDSLSQLFunctions.isnull(Integer.class,MC.mapID,0).as("MapID")
								, C.fabricName
								, Expressions.cases().when(
										MC.mapID.gt(0)
								).then(true).otherwise(false)
						)
				)
				.from(C)
				.innerJoin(CAT).on(expression)
				.leftJoin(MC).on(C.fabricId.eq(MC.fabricID).and(CAT.categoryId.eq(MC.categoryID)))
				.where(
						C.active.eq(true).and(CAT.lvl.eq(2)).and(CAT.categoryId.eq(categoryId))
				)
				.offset(offset)
				.limit(limit)
				.orderBy(C.fabricName.asc());


		QueryResults<CodeData> codeLengthEntityQueryResults = jpasqlQuery.fetchResults();
		List<CodeData> results = codeLengthEntityQueryResults.getResults();

		long resultsTotal = codeLengthEntityQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}

	@Override
	public Page<CodeData> findAllvwPatternCategoryByCategoryIdOrderByPatternName(Integer categoryId, int pageNumber, int pageSize) {
//		Create View dbo.vwPatternCategory
//				AS
//		select c.PatternID, cat.CategoryID, c.PatternName, isnull(m.MapID, 0) MapID
//				from
//		Code_Pattern c
//		inner join Category cat on 1=1
//		left outer join Map_Pattern_Category m on (c.PatternID = m.PatternID and cat.CategoryID = m.CategoryID)
//		where c.Active = 1 and cat.Lvl = 2

//						DataSrc = "vwPatternCategory";
//						ColumnList = "MapID,PatternID As CodeID,PatternName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "PatternName";
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;

		JPASQLQuery<CodeData> jpasqlQuery = new JPASQLQuery<CodeData>(entityManager,new MSSQLServer2012Templates());
		QCodePatternEntity C = QCodePatternEntity.codePatternEntity;
		QCategoryEntity CAT = QCategoryEntity.categoryEntity;
		QMapPatternCategoryEntity MC = QMapPatternCategoryEntity.mapPatternCategoryEntity;
		Expression<Integer> constant = Expressions.constant(1);
		BooleanExpression expression = Expressions.asNumber(1).eq(constant);

		jpasqlQuery
				.select(
						Projections.constructor(
								CodeData.class
								, C.patternId
								, queryDSLSQLFunctions.isnull(Integer.class,MC.mapID,0).as("MapID")
								, C.patternName
								, Expressions.cases().when(
										MC.mapID.gt(0)
								).then(true).otherwise(false)
						)
				)
				.from(C)
				.innerJoin(CAT).on(expression)
				.leftJoin(MC).on(C.patternId.eq(MC.patternID).and(CAT.categoryId.eq(MC.categoryID)))
				.where(
						C.active.eq(true).and(CAT.lvl.eq(2)).and(CAT.categoryId.eq(categoryId))
				)
				.offset(offset)
				.limit(limit)
				.orderBy(C.patternName.asc());


		QueryResults<CodeData> codeLengthEntityQueryResults = jpasqlQuery.fetchResults();
		List<CodeData> results = codeLengthEntityQueryResults.getResults();

		long resultsTotal = codeLengthEntityQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}
}
