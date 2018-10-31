/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response.show;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.common.SingleValueResult;
import net.fashiongo.webadmin.model.primary.show.ListShow;

/**
 * @author Sanghyup Kim
 */
@JsonSerialize
public class GetShowListResponse {
	@JsonProperty("Table")
	private List<SingleValueResult> singleValueResultList;

	@JsonProperty("Table1")
	private List<ListShow> showList;

	public List<SingleValueResult> getSingleValueResultList() {
		return singleValueResultList;
	}

	public void setSingleValueResultList(List<SingleValueResult> singleValueResultList) {
		this.singleValueResultList = singleValueResultList;
	}

	public List<ListShow> getShowList() {
		return showList;
	}

	public void setShowList(List<ListShow> showList) {
		this.showList = showList;
	}


	
}
