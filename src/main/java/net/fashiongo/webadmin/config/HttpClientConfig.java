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

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}
	
	private ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		return factory;
	}
		
}
