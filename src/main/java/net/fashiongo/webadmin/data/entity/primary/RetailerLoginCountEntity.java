package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "Retailer_LoginCount")
public class RetailerLoginCountEntity {

	@Getter
	@Embeddable
	public static class RetailerLoginCountVirtualPK implements Serializable {

		@Column(name = "HappendedOn")
		private String happendedOn;

		@Column(name = "RetailerID")
		private Integer retailerID;
	}

	@EmbeddedId
	private RetailerLoginCountVirtualPK id;

	@Column(name = "LoginCount")
	private Integer loginCount;
}
