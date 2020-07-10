package net.fashiongo.webadmin.service;


import net.fashiongo.webadmin.data.model.display.response.DisplayLocationResponse;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;

import java.util.List;

public interface DisplayService {
    CollectionObject<DisplayLocationResponse> getDisplayLocations();
}
