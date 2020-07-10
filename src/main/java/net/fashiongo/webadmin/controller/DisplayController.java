package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.data.model.display.response.DisplayLocationResponse;
import net.fashiongo.webadmin.service.DisplayService;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/display")
public class DisplayController {

    private final DisplayService displayService;

    public DisplayController(DisplayService displayService){
        this.displayService = displayService;
    }

    @GetMapping(value = "getLocation")
    public JsonResponse<CollectionObject<DisplayLocationResponse>> getDisplayLocation() {

        CollectionObject<DisplayLocationResponse> data = displayService.getDisplayLocation();
        return new JsonResponse<>(true, null, data);
    }
}
