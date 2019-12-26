package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.common.SubCategories;

import java.util.List;

public interface CategoryEntityRepositoryCustom {
    List<SubCategories> findAllByLvlAndParentCateID(Integer lvl, Integer parentCateId);
}
