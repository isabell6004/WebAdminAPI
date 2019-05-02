package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import net.fashiongo.webadmin.dao.primary.custom.EditorPickVendorContentRepositoryCustom;
import net.fashiongo.webadmin.model.primary.EditorPickVendorContent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-01
 */
@Repository
public interface EditorPickVendorContentRepository extends EditorPickVendorContentRepositoryCustom, JpaRepository<EditorPickVendorContent, Integer>,
JpaSpecificationExecutor<EditorPickVendorContent>, QuerydslPredicateExecutor<EditorPickVendorContent> {
	EditorPickVendorContent findOneByEditorPickVendorContentId(Integer editorPickVendorContentId);
	List<EditorPickVendorContent> findByVendorId(Integer vendorId);
}
