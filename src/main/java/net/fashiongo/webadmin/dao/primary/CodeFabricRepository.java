package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CodeFabric;

public interface CodeFabricRepository extends CrudRepository<CodeFabric, Integer> {
	CodeFabric findOneByFabricID(Integer fabricID);
}
