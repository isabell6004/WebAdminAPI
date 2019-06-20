package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ModelsOrdersResponse {
    private List<ModelUnit> models = new ArrayList<>(0);
    private List<SimplePhotoOrder> orders;
    private List<ModelOption> modelsOption = new ArrayList<>(0);
}
