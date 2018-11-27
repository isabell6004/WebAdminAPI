package net.fashiongo.webadmin.model.primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author DAHYE
 *
 */
@Entity
@Table(name = "List_CardStatus")
@Data
public class CardStatus {
	@Id
	@JsonProperty("CardStatusID")
	private Integer cardStatusID;
	
	@JsonProperty("CardStatusName")
	private String cardStatusName;
}
