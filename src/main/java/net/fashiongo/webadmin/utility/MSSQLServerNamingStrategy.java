/**
 * 
 */
package net.fashiongo.webadmin.utility;

import java.io.Serializable;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * @author kcha
 *
 */
public class MSSQLServerNamingStrategy extends PhysicalNamingStrategyStandardImpl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final MSSQLServerNamingStrategy INSTANCE = new MSSQLServerNamingStrategy();

	 @Override
	 public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
	     return new Identifier(name.getText(), name.isQuoted());
	 }

	 @Override
	 public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
	     return new Identifier(name.getText(), name.isQuoted());
	 }
}
