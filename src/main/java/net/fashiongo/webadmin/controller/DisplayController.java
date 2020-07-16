package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.data.model.display.DisplaySettingRequest;
import net.fashiongo.webadmin.data.model.display.response.*;
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
    public JsonResponse<SingleObject<Integer>> createDisplaySetting(@RequestBody DisplaySettingRequest displaySettingRequest) {

        SingleObject<Integer> data = displayService.createDisplaySetting(displaySettingRequest);
        return new JsonResponse<>(true, null, data);
    }

    @PutMapping(value = "updateSetting")
    public JsonResponse<Void> updateDisplaySetting(@RequestParam(value = "displaySettingId") int displaySettingId, @RequestBody DisplaySettingRequest displaySettingRequest) {
        displayService.updateDisplaySetting(displaySettingId, displaySettingRequest);
        return new JsonResponse<>(true, null, null);
    }

    @DeleteMapping(value = "deleteSetting")
    public JsonResponse<Void> deleteDisplaySetting(@RequestParam(value = "displaySettingId") int displaySettingId) {
          displayService.deleteDisplaySetting(displaySettingId);
          return new JsonResponse<>(true, null, null);
    }

    @GetMapping("/getSettingList")
    public net.fashiongo.common.JsonResponse<CollectionObject<DisplaySettingListResponse>> getDisplaySettingList(@RequestParam(value = "pn") int pageNum,
                                                                                                                 @RequestParam(value = "ps") int pageSize,
                                                                                                                 @RequestParam(value = "deviceType",required = false) Integer deviceType,
                                                                                                                 @RequestParam(value = "pageId",required = false) Integer pageId,
                                                                                                                 @RequestParam(value = "displayLocationId",required = false) Integer displayLocationId,
                                                                                                                 @RequestParam(value = "linkType",required = false) Integer linkType,
                                                                                                                 @RequestParam(value = "dateType",required = false) Integer dateType,
                                                                                                                 @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                                                                                 @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
                                                                                                                 @RequestParam(value = "displaySettingStatus", required = false) Integer displaySettingStatus,
                                                                                                                 @RequestParam(value = "title", required = false) String title,
                                                                                                                 @RequestParam(value = "linkCollectionId", required = false) Integer linkCollectionId) {
        CollectionObject<DisplaySettingListResponse> data = displayService.getDisplaySettingList(pageNum, pageSize, deviceType,pageId, displayLocationId,linkType,dateType, fromDate, toDate,displaySettingStatus,title, linkCollectionId);
        return new net.fashiongo.common.JsonResponse<>(true, null, data);
    }
}
