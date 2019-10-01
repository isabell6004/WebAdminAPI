package net.fashiongo.webadmin.data.repository.primary.form;

import net.fashiongo.webadmin.data.entity.primary.FashionGoFormEntity;

import java.util.List;

public interface FashionGoFormEntityRepositoryCustom {
	List<FashionGoFormEntity> getForms(FormOrderingType orderingType);
}
