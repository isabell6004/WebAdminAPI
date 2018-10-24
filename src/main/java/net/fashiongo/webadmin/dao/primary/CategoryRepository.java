package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
	List<Category> findByActiveTrue();
}