package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 
 * @author Reo
 *
 */
@Getter
@Builder
@AllArgsConstructor
public class CodeData {
	@JsonProperty("CodeID")
	private Integer codeID;
	
	@JsonProperty("MapID")
	private Integer mapID;
	
	@JsonProperty("CodeName")
	private String codeName;
	
	@JsonProperty("Active")
	private Boolean active;

	public Integer getCodeID() {
		return codeID;
	}

	public void setCodeID(Integer codeID) {
		this.codeID = codeID;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}
	
	
}
