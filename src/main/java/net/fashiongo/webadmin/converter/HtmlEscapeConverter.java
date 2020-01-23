package net.fashiongo.webadmin.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.web.util.HtmlUtils;

@Converter
public class HtmlEscapeConverter implements AttributeConverter<String, String>{

	@Override
	public String convertToDatabaseColumn(String attribute) {
		return HtmlUtils.htmlEscape(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return HtmlUtils.htmlEscape(dbData);
	}

}
