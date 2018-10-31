/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response.show;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.primary.show.ListShow;

/**
 * @author Sanghyup Kim
 */
@JsonSerialize
public class GetShowCategoriesResponse {
	@JsonProperty("ShowCategoryList")
	private List<ListShow> showCategoryList;

	public List<ListShow> getShowCategoryList() {
		return showCategoryList;
	}

	public void setShowCategoryList(List<ListShow> showCategoryList) {
		this.showCategoryList = showCategoryList;
	}

	
}
