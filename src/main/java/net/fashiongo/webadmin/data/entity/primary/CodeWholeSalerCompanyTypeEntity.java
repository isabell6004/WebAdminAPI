package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "Code_WholeSalerCompanyType")
@Getter
public class CodeWholeSalerCompanyTypeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CompanyTypeID")
	private Integer companyTypeID;

	@Column(name = "CompanyTypeName")
	private String companyTypeName;

	@Column(name = "Active")
	private boolean active = true;
}
