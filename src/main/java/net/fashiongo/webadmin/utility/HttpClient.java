package net.fashiongo.webadmin.utility;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class HttpClient {
	private String server;
	private RestTemplate rest;
	private HttpHeaders headers;
	private HttpStatus status;
	
	public HttpClient(String server, RestTemplate rest) {
		this.server = server;
		this.rest = rest;
		
		this.headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		headers.add("Accept", "*/*");
	}
	
	public JsonResponse get(String uri) {
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		
		ResponseEntity<JsonResponse> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, JsonResponse.class);
		this.setStatus(responseEntity.getStatusCode());
		return responseEntity.getBody();
	}
	
	public JsonResponse post(String uri, String json) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		
		ResponseEntity<JsonResponse> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, JsonResponse.class);
		this.setStatus(responseEntity.getStatusCode());
		return responseEntity.getBody();
	}
	
	public JsonResponse postObject(String uri, Object json){
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(json, headers);
		
		ResponseEntity<JsonResponse> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, JsonResponse.class);
		this.setStatus(responseEntity.getStatusCode());
		return responseEntity.getBody();
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
