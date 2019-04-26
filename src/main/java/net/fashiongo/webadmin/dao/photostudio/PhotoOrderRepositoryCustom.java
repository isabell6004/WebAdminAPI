package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoOrder;

public interface PhotoOrderRepositoryCustom {
	PhotoOrder getPhotoOrderInfoWithBookAndModelAndCategory(int orderId);
}
