package net.fashiongo.webadmin.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("jdbcHelper")
public class jdbcHelper {
private static Logger logger = LoggerFactory.getLogger(jdbcHelper.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Object> executeSP(String spName, List<Object> params, Class<?>... classList) {
		return executeSP(spName, params, null, classList);
	}
	
	public List<Object> executeSP(String spName, List<Object> params, List<Object> outputParams, Class<?>... classList) {

		Iterator<Class<?>> classIter = Arrays.asList(classList).iterator();
		  List<Object> r = jdbcTemplate.execute(
				   new CallableStatementCreator() {
					   @Override
					   public CallableStatement createCallableStatement(Connection con) throws SQLException {
						   int paramSize = params.size();
						   if(outputParams != null)
							   paramSize += outputParams.size();
						   String spStub = prepareCallStatement(spName, paramSize);
						   CallableStatement cStmt = con.prepareCall(spStub);
						   setSPParameters(cStmt, params, outputParams);
						   if(outputParams != null && !outputParams.isEmpty())
							   setSPOutputParameters(cStmt, outputParams, params.size() + 1);
						   
						   return cStmt;
					   }
				   }, 
				   new CallableStatementCallback<List<Object>>() {
					   @Override
					   public List<Object> doInCallableStatement(CallableStatement cs) throws SQLException {
						   List<Object> results = new ArrayList<Object>();
						   boolean resultsAvailable = cs.execute();
						   while(resultsAvailable) {
							   ResultSet rs = cs.getResultSet();
							   Class<?> cls = classIter.next();
							   List<Object> records = new ArrayList<Object>();
								while(rs.next()) {
									records.add(getObject(cls, rs));
								}
								results.add(records);
							   resultsAvailable = cs.getMoreResults();
						   }
						   if(outputParams != null) {
							   results.add(getOutputParameters(cs, outputParams, params.size() + 1));
						   }
						   return results;
					   }
				   }
			);
		  
		  return r;
	}

	public List<Object> getResult(CallableStatement cstmt, Class<?>... classList) {

		Iterator<Class<?>> classIter = Arrays.asList(classList).iterator();
		List<Object> results = new ArrayList<Object>();
		try {
			do {
				ResultSet rs = cstmt.getResultSet();
			
				Class<?> cls = classIter.next();
				List<Object> records = new ArrayList<Object>();
				while(rs.next()) {
					records.add(getObject(cls, rs));
				}
			
				results.add(records);
				
			} while(cstmt.getMoreResults());
		} catch (Exception e) {
			logger.error("JdbcHelper.getResult(), " + e.getMessage());
			return null;
		}
		
		return results;
	}
	
	public <T> T getObject(Class<T> cls, ResultSet rs) {
		T obj = null;
		try {
			//Added by Andy Min - 04/22/2015
			Set<String> tempRSField = new HashSet<String>();
			try {
				for (int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
					tempRSField.add(rs.getMetaData().getColumnName(i).toLowerCase());
				}
			} catch (Exception e) {
				logger.error("JdbcHelper.getObject().tempRSField.add()" + e.getMessage());
			}
			//Andy Min
			
			obj = cls.newInstance();
			for(Field f : obj.getClass().getDeclaredFields()) {
				if(!f.isAccessible()) f.setAccessible(true);
				
				/* k.c 7/27/2015
				Transient annotationTransient = f.getAnnotation(Transient.class);
				if(annotationTransient != null)
					continue;
				*/
				
				String columnName = f.getName();
				Column annotationColumn = f.getAnnotation(Column.class);
				if(annotationColumn != null)
					columnName = annotationColumn.name();
				
				//logger.info(columnName);
				try {
					//f.set(obj, rs.getObject(columnName));
					//Added by Andy Min - 04/22/2015
					if (tempRSField.contains(columnName.toLowerCase())) {
						//logger.info("---> " + f.getName());
						Object cv = rs.getObject(columnName);
						if(cv.getClass().getName().equals("java.sql.Timestamp")) f.set(obj, ((Timestamp)cv).toLocalDateTime());
						//logger.info(cv.getClass().getName());
						else f.set(obj, cv);
					}
					//Andy Min
				} 
				catch (Exception e) {
					//Added by Andy Min - 04/14/2015
					//logger.error("Exception - JdbcHelper.getObject(): " + e.getMessage(), e);
					//Andy Min
				}
			}
			
			// automatic execution of transient fields' getMethod to set transient fields
			for(Field f : obj.getClass().getFields()) {
				if(!f.isAccessible()) f.setAccessible(true);
				
				Transient ta = f.getAnnotation(Transient.class);
				if(ta == null) continue;
				
				String fn = f.getName();
				String methodName = new StringBuilder("get").append(fn.substring(0,1).toUpperCase()).append(fn.substring(1)).toString();
				//logger.debug("----------> method to be executed: {}", methodName);
				try {
					Method m = obj.getClass().getMethod(methodName, new Class<?>[] {});
					m.invoke(obj);
				} catch(Exception e) {
					logger.error("JdbcHelper.getObject().method.invoke.getMethod()" + e.getMessage());
				}
			}
			
		} catch (Exception e) {
			logger.error("JdbcHelper.getObject().OverAll, " + e.getMessage());
		}		
		
		return obj;
	}
	
	private String prepareCallStatement(String spName, int paramCount) {
		String callStatement = "{call " + spName + "(";
		
		for(int i=0; i<paramCount; i++)
			callStatement += "?,";
		
		if(paramCount > 0)
			callStatement = callStatement.substring(0, callStatement.length()-1);
		
		callStatement += ")}";
		
		return callStatement;
	}
	private void setSPParameters(CallableStatement cstmt, List<Object> params, List<Object> oParams) {
		int i = 1;
		try {
			for(Object obj : params) {
				if(obj == null) {
					//logger.info(i + " type: null");
					cstmt.setNull(i,  Types.NULL);
					i++;
					continue;
				}
				//logger.info(i + " type:" + obj.getClass().getName());
				switch(obj.getClass().getName()) {
					case "java.lang.String": cstmt.setString(i, (String)obj);
						break;
					case "java.lang.Integer": cstmt.setInt(i, (Integer)obj);
						break;
					case "java.lang.Float": cstmt.setBigDecimal(i, (BigDecimal)obj);
						break;
					case "java.math.BigDecimal": cstmt.setBigDecimal(i, (BigDecimal)obj);
						break;
					case "java.lang.Boolean": cstmt.setBoolean(i, (Boolean)obj);
						break;
					case "java.util.Date": cstmt.setDate(i, new java.sql.Date(((java.util.Date)obj).getTime()));
						break;
					default: cstmt.setNull(i, Types.NULL);
						break;
				}
				i++;
			}
			
		}
		catch (Exception x) {}
	}
	
	private void setSPOutputParameters(CallableStatement cstmt, List<Object> params, int startIndex) {
		try {
			for(Object obj : params) {
				switch(obj.getClass().getName()) {
					case "java.lang.String": cstmt.registerOutParameter(startIndex, Types.NVARCHAR);
					break;
				case "java.lang.Integer": cstmt.registerOutParameter(startIndex, Types.INTEGER);
					break;
				case "java.lang.Float": cstmt.registerOutParameter(startIndex, Types.DECIMAL);
					break;
				case "java.math.BigDecimal": cstmt.registerOutParameter(startIndex, Types.DECIMAL);
					break;
				case "java.lang.Boolean": cstmt.registerOutParameter(startIndex, Types.BIT);
					break;
				case "java.util.Date": cstmt.registerOutParameter(startIndex, Types.DATE);
					break;
				default:
					break;
				}
				startIndex++;
			}
		}
		catch (Exception x) {}
	}
	
	private List<Object> getOutputParameters(CallableStatement cstmt, List<Object> params, int startIndex) {
		List<Object> result = new ArrayList<Object>();
		try {
			for(Object obj : params) {
				switch(obj.getClass().getName()) {
					case "java.lang.String": result.add(cstmt.getString(startIndex));
					break;
				case "java.lang.Integer": result.add(cstmt.getInt(startIndex));
					break;
				case "java.lang.Float": result.add(cstmt.getFloat(startIndex));
					break;
				case "java.math.BigDecimal": result.add(cstmt.getBigDecimal(startIndex));
					break;
				case "java.lang.Boolean": result.add(cstmt.getBoolean(startIndex));
					break;
				case "java.util.Date": result.add(cstmt.getDate(startIndex));
					break;
				default:
					break;
				}
				startIndex++;
			}
		}
		catch (Exception x) {}
		
		return result;
	}
}
