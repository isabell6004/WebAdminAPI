package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CodeLength;
import net.fashiongo.webadmin.model.primary.CodePattern;

public interface CodeLengthRepository extends CrudRepository<CodeLength, Integer> {
	CodeLength findOneByLengthID(Integer lengthID);
}
