/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.ad.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.pojo.ad.FGListADCalendar;

@JsonSerialize
public class GetFGCategoryListAdCountResponse {
	@JsonProperty("Table")
	private List<FGListADCalendar> fgCalendarList;

	public List<FGListADCalendar> getFgCalendarList() {
		return fgCalendarList;
	}

	public void setFgCalendarList(List<FGListADCalendar> fgCalendarList) {
		this.fgCalendarList = fgCalendarList;
	}
	
}