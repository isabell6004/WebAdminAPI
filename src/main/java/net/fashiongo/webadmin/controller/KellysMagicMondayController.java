package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.kmm.KmmDetail;
import net.fashiongo.webadmin.service.renewal.RenewalKMMService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value="/kmm", produces = "application/json")
public class KellysMagicMondayController {

    @Autowired
    RenewalKMMService renewalKMMService;

    @GetMapping("/{trendReportId}")
    public JsonResponse<KmmDetail> getKmmDeatil(@PathVariable("trendReportId") Integer trendReportId) {
        JsonResponse<KmmDetail> response = new JsonResponse<>(false, null, null);

        try {
            KmmDetail data = renewalKMMService.getKmmDetail(trendReportId);

            response.setSuccess(true);
            response.setData(data);
            response.setMessage("success");
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            response.setMessage("fail");
        }

        return response;
    }
}
