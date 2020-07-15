package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.data.model.display.DisplaySettingParameter;
import net.fashiongo.webadmin.data.model.display.response.DisplayCalendarResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplayCollectionResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplayLocationResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplaySettingResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.DisplayService;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/display", produces = "application/json")
public class DisplayController {

    private final DisplayService displayService;

    public DisplayController(DisplayService displayService){
        this.displayService = displayService;
    }

    @GetMapping(value = "getLocations")
    public JsonResponse<CollectionObject<DisplayLocationResponse>> getDisplayLocations() {

        CollectionObject<DisplayLocationResponse> data = displayService.getDisplayLocations();
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping(value = "getCollections")
    public JsonResponse<CollectionObject<DisplayCollectionResponse>> getCollections() {

        CollectionObject<DisplayCollectionResponse> data = displayService.getDisplayCollections();
        return new JsonResponse<>(true, null, data);
    }


    @GetMapping(value = "getCalendar")
    public JsonResponse<CollectionObject<DisplayCalendarResponse>> getDisplayCalendar(@RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                                      @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        CollectionObject<DisplayCalendarResponse> data = displayService.getDisplayCalendar(startDate,endDate);
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping(value = "getSetting")
    public JsonResponse<SingleObject<DisplaySettingResponse>> getDisplaySetting(@RequestParam(value = "displaySettingId") int displaySettingId) {

        SingleObject<DisplaySettingResponse> data = displayService.getDisplaySetting(displaySettingId);
        return new JsonResponse<>(true, null, data);
    }

    @PostMapping(value = "createSetting")
    public JsonResponse<SingleObject<Integer>> createDisplaySetting(@RequestBody DisplaySettingParameter displaySettingParameter) {

        SingleObject<Integer> data = displayService.createDisplaySetting(displaySettingParameter);
        return new JsonResponse<>(true, null, data);
    }

    @PutMapping(value = "updateSetting")
    public JsonResponse<Void> updateDisplaySetting(@RequestParam(value = "displaySettingId") int displaySettingId, @RequestBody DisplaySettingParameter displaySettingParameter) {
        displayService.updateDisplaySetting(displaySettingId, displaySettingParameter);
        return new JsonResponse<>(true, null, null);
    }

    @DeleteMapping(value = "deleteSetting")
    public JsonResponse<Void> deleteDisplaySetting(@RequestParam(value = "displaySettingId") int displaySettingId) {
          displayService.deleteDisplaySetting(displaySettingId);
          return new JsonResponse<>(true, null, null);
    }
}
