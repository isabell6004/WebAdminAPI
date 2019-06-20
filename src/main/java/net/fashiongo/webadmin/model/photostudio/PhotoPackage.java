package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Photo_Package")
@Getter
@Setter
public class PhotoPackage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PackageID")
	private Integer id;

	@Column(name = "PackageName")
	private String name;

	@Column(name = "PackageDescription")
	private String description;

	@Column(name = "IsPackageOption")
	private Boolean isOption;

	@Column(name = "CreatedOn", updatable = false)
	private Date createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Date modifiedOn;

	@Column(name = "ModifiedBY")
	private String modifiedBy;

	@Column(name = "CategoryID")
	private Integer categoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID", insertable = false, updatable = false)
	private PhotoCategory photoCategory;
}
