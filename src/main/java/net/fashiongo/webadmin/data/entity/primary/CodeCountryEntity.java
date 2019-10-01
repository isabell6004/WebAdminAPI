package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "Code_Country")
public class CodeCountryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CountryID")
	private Integer countryID;

	@Column(name = "CountryCode")
	private String countryCode;

	@Column(name = "CountryName")
	private String countryName;

	@Column(name = "SortNo")
	private int sortNo;

	@Column(name = "Active")
	private Boolean active = true;

	@Column(name = "InsertUser")
	private String insertUser;

	@Column(name = "InsertDate")
	private Timestamp insertDate;

	@Column(name = "UpdateUser")
	private String updateUser;

	@Column(name = "UpdateDate")
	private Timestamp updateDate;

}
