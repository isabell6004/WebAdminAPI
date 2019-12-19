package net.fashiongo.webadmin.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.kmm.KmmCandidateItems;
import net.fashiongo.webadmin.data.model.kmm.KmmDetail;
import net.fashiongo.webadmin.data.model.kmm.KmmSavePayload;
import net.fashiongo.webadmin.data.model.kmm.SaveKmmRequest;
import net.fashiongo.webadmin.service.renewal.RenewalKMMService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/{trendReportId}/candidateItems")
    public JsonResponse<List<KmmCandidateItems>> getKmmCandidateItems(@PathVariable("trendReportId") Integer trendReportId) {
        JsonResponse<List<KmmCandidateItems>> response = new JsonResponse<>(false, null,null);

        try {
            List<KmmCandidateItems> data = renewalKMMService.getKmmCandidateItems(trendReportId);

            response.setSuccess(true);
            response.setData(data);
            response.setMessage("success");
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            response.setMessage("fail");
        }

        return response;
    }

    @GetMapping("/{trendReportId}/items")
    public JsonResponse<List<KmmCandidateItems>> getKmmSelectedItems(@PathVariable("trendReportId") Integer trendReportId) {
        JsonResponse<List<KmmCandidateItems>> response = new JsonResponse<>(false, null,null);

        try {
            List<KmmCandidateItems> data = renewalKMMService.getKmmSelectedItems(trendReportId);

            response.setSuccess(true);
            response.setData(data);
            response.setMessage("success");
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            response.setMessage("fail");
        }

        return response;
    }

    @PostMapping("/save")
    public JsonResponse saveKmm(@RequestBody SaveKmmRequest payload) {
        JsonResponse response = new JsonResponse(false, null, null);
        ObjectMapper mapper = new ObjectMapper();

        try {
            KmmSavePayload savePayload = mapper.readValue(payload.getPayload(), KmmSavePayload.class);

            Boolean success = false;
            String message = "";

            if (StringUtils.isNotEmpty(payload.getPayload())) {
                success = renewalKMMService.saveKmm(savePayload);
                message = success ? "Successfully updated!" : "Error occurred";

                response.setSuccess(success);
                response.setMessage(message);
            }
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            response.setMessage("Error occurred");
        }

        return response;
    }
}
