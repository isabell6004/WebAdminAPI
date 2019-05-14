package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PhotoUnitListResponse {
    List<PhotoUnitResponse> currentPhotoUnits;
    List<PhotoUnitResponse> newPhotoUnits;
}
