package net.fashiongo.webadmin.model.pojo.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.CategoryReport;

/**
 *
 * @author Incheol Jung
 */
public class GetTrendReportCategoryResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("CategoryList")
	private List<CategoryReport> categoryList;
	public List<CategoryReport> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CategoryReport> categoryList) {
		this.categoryList = categoryList;
	}

}
