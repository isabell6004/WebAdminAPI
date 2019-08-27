package net.fashiongo.webadmin.data.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimpleTemplate;
import org.springframework.stereotype.Component;

@Component(value = "sqlServerFunction")
public class QueryDSLSQLFunctionsSQLServer implements QueryDSLSQLFunctions {

	static final String ISNULL_TEMPLATE = "ISNULL({0},{1})";

	static final String UFN_MIN_VALUE = "dbo.ufnMinValue({0},{1})";

	static final String UFN_MAX_VALUE = "dbo.ufnMaxValue({0},{1})";

	@Override
	public <T> SimpleTemplate<T> isnull(Class<? extends T> cl, Object arg1,Object arg2) {
		return Expressions.simpleTemplate(cl,ISNULL_TEMPLATE,arg1,arg2);
	}

	@Override
	public <T> SimpleTemplate<T> ufnMinValue(Class<? extends T> cl, Object arg1, Object arg2) {
		return Expressions.simpleTemplate(cl,UFN_MIN_VALUE,arg1,arg2);
	}

	@Override
	public <T> SimpleTemplate<T> ufnMaxValue(Class<? extends T> cl, Object arg1,Object arg2) {
		return Expressions.simpleTemplate(cl,UFN_MAX_VALUE,arg1,arg2);
	}
}
