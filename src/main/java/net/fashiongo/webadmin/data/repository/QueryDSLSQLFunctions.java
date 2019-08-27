package net.fashiongo.webadmin.data.repository;


import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimpleTemplate;

public interface QueryDSLSQLFunctions {

	<T> SimpleTemplate<T> isnull(Class<? extends T> cl, Object arg1,Object arg2);

	<T> SimpleTemplate<T> ufnMinValue(Class<? extends T> cl, Object arg1, Object arg2);

	<T> SimpleTemplate<T> ufnMaxValue(Class<? extends T> cl, Object arg1,Object arg2);
}
