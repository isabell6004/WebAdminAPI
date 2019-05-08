package net.fashiongo.webadmin.model.photostudio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

}
