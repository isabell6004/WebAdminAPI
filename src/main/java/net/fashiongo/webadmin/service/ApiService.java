/**
 * 
 */
package net.fashiongo.webadmin.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import net.fashiongo.webadmin.common.jdbcHelper;

public class ApiService {
	protected final Logger logger = LogManager.getLogger();
	
	@Autowired
	protected jdbcHelper jdbcHelper;
	
}