package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.data.model.response.reward.RewardApiResponse;
import net.fashiongo.webadmin.service.RewardService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/reward")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @RequestMapping(value = "**")
    public ResponseEntity<?> proxyRewardApi(@RequestBody(required = false) Object body, HttpServletRequest request) {
        try {
            return new ResponseEntity<>(
                    rewardService.getApiResponse(
                            request.getRequestURI(),
                            HttpMethod.valueOf(request.getMethod()),
                            Utility.getUsername(),
                            request.getQueryString(),
                            body),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("RewardController.proxyRewardApi() uri={} query={}", request.getRequestURI(), request.getQueryString(), e);
            return new ResponseEntity<>(new RewardApiResponse<>(false, e.getMessage(), null), HttpStatus.OK);
        }
    }
}
