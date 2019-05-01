package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.MapPhotoImage;

import java.util.List;

public interface MapPhotoImageRepositoryCustom {
	List<MapPhotoImage> findModelImagesByModelIds(List<Integer> modelIds);
}
