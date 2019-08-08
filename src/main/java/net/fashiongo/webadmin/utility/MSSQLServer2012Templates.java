package net.fashiongo.webadmin.utility;

import com.querydsl.sql.SQLServer2012Templates;

public class MSSQLServer2012Templates extends SQLServer2012Templates {

	@Override
	protected boolean requiresQuotes(String identifier, boolean precededByDot) {

		if(isQuoted(identifier)) {
			return false;
		}

		return super.requiresQuotes(identifier, precededByDot);
	}

	public boolean isQuoted(String name) {
		return ( name.startsWith( "`" ) && name.endsWith( "`" ) )
				|| ( name.startsWith( "[" ) && name.endsWith( "]" ) )
				|| ( name.startsWith( "\"" ) && name.endsWith( "\"" ) );
	}
}
