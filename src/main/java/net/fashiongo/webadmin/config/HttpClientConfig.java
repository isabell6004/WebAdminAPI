/**
 * 
 */
package net.fashiongo.webadmin.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import net.fashiongo.webadmin.utility.HttpClient;


/**
 * @author incheol
 *
 */
@Configuration
public class HttpClientConfig {

	@Value("${webAdminapi.fgServiceAPI_EndPoint}")
	private String fgService;
	
	@Value("${webAdminapi.fgWebAdminAPI_EndPoint}")
	private String fgWebAdminService;
	
	@Value("${webAdminapi.fgVendorAPI_EndPoint}")
	private String fgVendorApiService;
	
	@Value("${webAdminapi.statsAPI_EndPoint}")
	private String statsApiService;

	@Value("${webAdminapi.fgPaymentAPI_EndPoint}")
	private String fgPaymentApiService;
	
	@Bean(name = "serviceJsonClient")
	public HttpClient serviceJsonClient() {
		HttpClient httpClient = new HttpClient(fgService, restTemplate());
		return httpClient;
	}
	
	@Bean(name = "webAdminJsonClient")
	public HttpClient webAdminJsonClient() {
		HttpClient httpClient = new HttpClient(fgWebAdminService, restTemplate());
		return httpClient;
	}
	
	@Bean(name = "vendorApiJsonClient")
	public HttpClient vendorApiJsonClient() {
		HttpClient httpClient = new HttpClient(fgVendorApiService, restTemplate());
		return httpClient;
	}
	
	@Bean(name = "statsApiJsonClient")
	public HttpClient statsApiJsonClient() {
		return new HttpClient(statsApiService, restTemplate());
	}

	@Bean(name = "paymentApiJsonClient")
	public HttpClient paymentApiJsonClient() {
		return new HttpClient(fgPaymentApiService, restTemplate());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}
	
	private ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		return factory;
	}



}
