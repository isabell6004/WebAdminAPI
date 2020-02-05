package net.fashiongo.webadmin.service;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.data.model.response.reward.RewardApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Service
public class RewardService {
    @Autowired
    @Qualifier("rewardApiRestTemplate")
    private RestTemplate restTemplate;

    @Value("${webAdminapi.rewardApi.endpoint}")
    private String rewardApiEndpoint;

    public void shippingConfirmReward(String userName, int buyerId, int vendorId, int orderId) throws Exception {
        getData(
                "/reward/buyer/shippingConfirmReward",
                HttpMethod.POST,
                userName,
                "buyerId=" + buyerId + "&vendorId=" + vendorId + "&orderId=" + orderId,
                null);
    }

    private Object getData(String endpoint, HttpMethod method, String userName, String queries, Object body) throws Exception {
        try {
            RewardApiResponse<?> apiResponse = getApiResponse(endpoint, method, userName, queries, body);
            if (apiResponse == null) throw new Exception("No apiResponse from reward-api");
            if (!apiResponse.isSuccess())
                throw new Exception("reward-api isSuccess:false, message:" + apiResponse.getMessage());
            return apiResponse.getData();
        } catch (Exception e) {
            log.error("reward-api exception", e);
            throw new Exception("reward-api exception");
        }
    }

    public RewardApiResponse<?> getApiResponse(String endpoint, HttpMethod method, String userName, String queries, Object body) throws Exception {
        String uri = rewardApiEndpoint + endpoint + "?" + queries;
        String uuid = UUID.randomUUID().toString();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("requestFrom", "webAdmin");
        httpHeaders.add("requestUuid", uuid);
        httpHeaders.add("userName", userName);
        log.info("RewardService.getApiResponse() uri={} uuid={} userName={}", uri, uuid, userName);
        HttpEntity<Object> httpEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<?> response = this.restTemplate.exchange(uri, method, httpEntity, RewardApiResponse.class);
        if (response == null) throw new Exception("No response from reward-api");
        return (RewardApiResponse<?>) response.getBody();
    }
}
