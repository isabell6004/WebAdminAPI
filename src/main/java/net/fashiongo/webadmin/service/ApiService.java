/**
 * 
 */
package net.fashiongo.webadmin.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import net.fashiongo.common.dal.JdbcHelper;

public class ApiService {
	protected final Logger logger = LogManager.getLogger();
	
	@Autowired
	protected JdbcHelper jdbcHelper;

	// message constant
	final String MSG_SAVE_SUCCESS = "Saved successfully!";
	final String MSG_UPDATE_SUCCESS = "Updated successfully!";
	final String MSG_INSERT_SUCCESS = "Inserted successfully!";
	final String MSG_DELETE_SUCCESS = "Deleted successfully!";
	final String MSG_CHANGE_SUCCESS = "Changed successfully!";

}