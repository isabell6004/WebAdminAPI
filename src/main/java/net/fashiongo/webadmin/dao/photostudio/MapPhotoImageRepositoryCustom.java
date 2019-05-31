package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.MapPhotoImage;

import java.util.List;

public interface MapPhotoImageRepositoryCustom {
	List<MapPhotoImage> findModelImageByModelId(Integer modelId);
	List<MapPhotoImage> findModelImagesByModelIds(List<Integer> modelIds);
	List<MapPhotoImage> findPriceImagesByPriceIds(List<Integer> priceIds);
}
