package net.fashiongo.webadmin.support.storage;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SwiftAuth {

	private final SwiftProperties swiftConfig;

	private ZonedDateTime expireDateTime;

	private String accessToken;

	private RestTemplate restTemplate = new RestTemplate();

	public SwiftAuth(SwiftProperties swiftConfig) {
		this.swiftConfig = swiftConfig;
	}

	private void setAuth() throws RuntimeException {

		// set header
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON_UTF8);

			// generate request body
			Map<String, String> passwordCredentials = new HashMap<>();
			passwordCredentials.put("username", swiftConfig.getUsername());
			passwordCredentials.put("password", swiftConfig.getPassword());

			Map<String, Object> auth = new HashMap<>();
			auth.put("tenantId", swiftConfig.getTenantId());
			auth.put("passwordCredentials", passwordCredentials);

			Map<String, Object> requestBody = new HashMap<>();
			requestBody.put("auth", auth);

			ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(swiftConfig.getAuthApiUrl(), new HttpEntity(requestBody, header), String.class);


			JSONObject jsonResult = new JSONObject(stringResponseEntity.getBody());
			JSONObject accessTokenInfo = jsonResult.getJSONObject("access").getJSONObject("token");

			String expireStr = accessTokenInfo.getString("expires");
			DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
			expireDateTime = ZonedDateTime.parse(expireStr, formatter);
			accessToken = accessTokenInfo.getString("id");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getAccessToken() {
		// FIXME 타이밍 문제로 accessToken이 만료된체로 요청이 가는 경우가 있어 30분전에 토큰을 refresh 하도록 변경하였음. 개선이 필요한 로직
		if (StringUtils.isEmpty(accessToken) || expireDateTime == null || ZonedDateTime.now(ZoneId.of("GMT")).plusMinutes(30).isAfter(expireDateTime)) {
			setAuth();
		}

		return accessToken;
	}
}
