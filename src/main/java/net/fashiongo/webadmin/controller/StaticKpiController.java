/**
 * 
 */
package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.statistics.GetFGKPIParameter;
import net.fashiongo.webadmin.utility.DateUtils;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author Incheol Jung
 */
@Slf4j
@RestController
@RequestMapping(value="/statkpi", produces = "application/json")
public class StaticKpiController {

	@Autowired
	@Qualifier("statsApiJsonClient")
	private HttpClient httpClient;

	@PostMapping(value = "getfgkpi")
	public JsonResponse getFGKPI(@RequestBody GetFGKPIParameter parameter) {

		UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromPath("/kpi/fg");

//		int? pn = string.IsNullOrEmpty(param["pn"]) ? 0 : Convert.ToInt32($"{param["pn"]}");
//		int? ps = string.IsNullOrEmpty(param["ps"]) ? 0 : Convert.ToInt32($"{param["ps"]}");
//		int? unit = string.IsNullOrEmpty(param["unit"]) ? 0 : Convert.ToInt32($"{param["unit"]}");
//		var df = ConvertDateTime($"{param["df"]}", "F");
//		var dt = ConvertDateTime($"{param["dt"]}", "T");
//		var orderBy = $"{param["orderBy"]}";
//		if (orderBy.Contains("undefined")) orderBy = "";

		Integer pn = Optional.ofNullable(parameter.getPn()).orElse(0);
		Integer ps = Optional.ofNullable(parameter.getPs()).orElse(0);
		Integer unit = Optional.ofNullable(parameter.getUnit()).orElse(0);
		LocalDateTime df = DateUtils.convertToLocalDateTime(parameter.getDf(),"F");
		LocalDateTime dt = DateUtils.convertToLocalDateTime(parameter.getDt(),"T");
		String orderBy = Optional.ofNullable(parameter.getOrderBy()).filter(s -> StringUtils.hasLength(s)).orElse("");

		if(orderBy.contains("undefined")) {
			orderBy = "";
		}

		if(pn > 0 ) {
			componentsBuilder.queryParam("pn",pn);
		}

		if(ps > 0 ) {
			componentsBuilder.queryParam("ps",ps);
		}

		if(unit > 0 ) {
			componentsBuilder.queryParam("unit",unit);
		}

		if(df != null) {
			componentsBuilder.queryParam("df",df.toLocalDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		}

		if(dt != null) {
			componentsBuilder.queryParam("dt",dt.toLocalDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		}

		if(StringUtils.hasLength(orderBy)) {
			componentsBuilder.queryParam("orderBy",orderBy);
		}

		String uri = componentsBuilder.build().toUriString();

		return httpClient.get(uri);
	}
}
