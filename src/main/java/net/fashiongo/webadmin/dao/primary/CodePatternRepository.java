package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CodePattern;

public interface CodePatternRepository extends CrudRepository<CodePattern, Integer> {
	CodePattern findOneByPatternID(Integer patternID);
}
