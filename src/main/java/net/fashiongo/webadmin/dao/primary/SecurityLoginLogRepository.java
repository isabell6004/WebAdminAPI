package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityLoginLog;

public interface SecurityLoginLogRepository extends CrudRepository<SecurityLoginLog, Integer>{
}