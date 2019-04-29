package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.dal.IPersistent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-15
 */
@Entity
@Table(name = "tblWholeSaler")
@Getter
@Setter
public class Vendor implements IPersistent, Serializable {
	private static final long serialVersionUID = -5598542682840377145L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WholeSalerID")
	private Integer wholeSalerId;
	
	@Column(name="CompanyName")
	private String companyName;
	
	@Column(name="Active")
	private Boolean active;
	
	@Column(name="ShopActive")
	private Boolean shopActive;
	
	@Column(name="OrderActive")
	private Boolean orderActive;
	
	@Column(name="Vendor_Type")
	private Integer vendorType;
}
