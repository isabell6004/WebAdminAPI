package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.data.model.display.DisplaySettingRequest;
import net.fashiongo.webadmin.data.model.display.response.DisplayCalendarResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplayLocationResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplaySettingResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.DisplayService;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/display")
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
    public JsonResponse<Integer> createDisplaySetting(@RequestBody @Valid DisplaySettingRequest displaySettingRequest) {

        int displaySettingId = displayService.createDisplaySetting(displaySettingRequest);
        return new JsonResponse<>(true, null, displaySettingId);
    }

    @PutMapping(value = "updateSetting")
    public ResultCode updateDisplaySetting(@RequestParam(value = "displaySettingId") int displaySettingId, @RequestBody @Valid DisplaySettingRequest displaySettingRequest) {
        try {
            displayService.updateDisplaySetting(displaySettingId, displaySettingRequest);
            return new ResultCode(true, 1, "success");
        } catch (Exception e) {
            return new ResultCode(false, -1, "failure");
        }
    }

    @DeleteMapping(value = "deleteSetting")
    public ResultCode deleteDisplaySetting(@RequestParam(value = "displaySettingId") int displaySettingId) {
        try {
            displayService.deleteDisplaySetting(displaySettingId);
            return new ResultCode(true, 1, "success");
        } catch (Exception e) {
            return new ResultCode(false, -1, "failure");
        }
    }
}
