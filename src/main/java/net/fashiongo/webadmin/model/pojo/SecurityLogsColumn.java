package net.fashiongo.webadmin.model.pojo;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class SecurityLogsColumn {
	@Column(name = "Column1")
	@JsonProperty("Column1")
	private Integer column1;

	public Integer getColumn1() {
		return column1;
	}

	public void setColumn1(Integer column1) {
		this.column1 = column1;
	}
}
