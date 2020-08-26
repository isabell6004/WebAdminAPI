/**
 * 
 */
package net.fashiongo.webadmin.controller;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;

import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author kcha
 *  wrapper for FashionGo Service API
 */
@RestController
@RequestMapping(value = "/service", produces = "application/json")
public class FGServiceController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("serviceJsonClient")
	HttpClient jsonClient;
	
	@RequestMapping(value = "/pdf/photostudio/receipt/{wholeSalerID}/{orderID}", method = RequestMethod.GET)
	public JsonResponse<String> getOrderReceiptPDF(HttpServletRequest request) {
		String url = extractUri(request.getRequestURL().toString() + "?" + request.getQueryString());
		logger.debug("called service url: " + url);
		return jsonClient.get(url);
	}
	
	@RequestMapping(value = "/pdf/photostudio/order/{orderID}", method = RequestMethod.GET)
	public JsonResponse<String> getOrderDetailPDF(HttpServletRequest request) {
		String url = extractUri(request.getRequestURL().toString() + "?" + request.getQueryString(), "webAdminGuid");
		logger.debug("called service url: " + url);
		return jsonClient.get(url);
	}
	@RequestMapping(value = "/v2/pdf/photostudio/order/{orderID}", method = RequestMethod.GET)
	public JsonResponse<String> getV2OrderDetailPDF(HttpServletRequest request) {
		String url = extractUri(request.getRequestURL().toString(), "webAdminGuid");
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url)
				.query(request.getQueryString());
		Optional.ofNullable(Utility.getUsername())
				.ifPresent(s -> uriComponentsBuilder.queryParam("requestor",s));
		url = uriComponentsBuilder.build().toUriString();
		logger.debug("called service url: " + url);
		return jsonClient.get(url);
	}
	
	@RequestMapping(value = "/pdf/photostudio/order/dailysummary/{photoshootDate}", method = RequestMethod.GET)
	public JsonResponse<String> getOrderSummaryPDF(HttpServletRequest request) {
		String url = extractUri(request.getRequestURL().toString() + "?" + request.getQueryString());
		logger.info("called service url: " + url);
		return jsonClient.get(url);
	}
	@RequestMapping(value = "/v2/pdf/photostudio/order/dailysummary/{photoshootDate}", method = RequestMethod.GET)
	public JsonResponse<String> getV2OrderSummaryPDF(HttpServletRequest request) {
		String url = extractUri(request.getRequestURL().toString());
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url)
				.query(request.getQueryString());
		Optional.ofNullable(Utility.getUsername())
				.ifPresent(s -> uriComponentsBuilder.queryParam("requestor",s));
		url = uriComponentsBuilder.build().toUriString();
		logger.debug("called service url: " + url);
		return jsonClient.get(url);
	}

	
	private String extractUri(String url) {
		return extractUri(url, null);
	}
	
	/* example: 
	 *     http://api.fashiongo.net/service/export/order?t=w&merged=true
	 * --> http://service.fashiongo.net/export/{guid}/order?t=w&merged=true
	 */
	private String extractUri(String url, String replacement) {
		
		Pattern p = Pattern.compile("^(http|https)://.+?/service/(.+)/(.+?)$");
		Matcher m = p.matcher(url);
		String uri = "";
		if(m.matches()) {
			uri = m.group(2) + "/" + (!StringUtils.isEmpty(replacement)? (replacement + "/"):"") + m.group(3);
		}
		
		return "/" + uri;
	}
}
