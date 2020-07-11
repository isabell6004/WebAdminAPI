package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.data.model.display.response.DisplayCalendarResponse;
import net.fashiongo.webadmin.data.model.display.response.DisplayLocationResponse;
import net.fashiongo.webadmin.service.DisplayService;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
