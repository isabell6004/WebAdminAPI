package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CodeStyle;

public interface CodeStyleRepository extends CrudRepository<CodeStyle, Integer> {
	CodeStyle findOneByStyleID(Integer styleID);
}
