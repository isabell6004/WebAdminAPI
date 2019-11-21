package net.fashiongo.webadmin.data.entity.primary.vendor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tblWholeSaler")
public class WholesalerCompanyEntity {
	@Id
	@Column(name = "WholeSalerID")
	@Setter(AccessLevel.NONE)
	private Integer wholesalerId;

	@Column(name = "CompanyName")
	private String companyName;

	@Column(name = "Active")
	private Boolean active;
}
