package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "wa_SavedSearch")
@DynamicUpdate
public class WASavedSearchEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SavedId")
	private Integer savedId;

	@Column(name = "SavedName")
	private String savedName;

	@Column(name = "SavedType")
	private String savedType;

	@Column(name = "DisplayStr")
	private String displayStr;

	@Column(name = "FilterStr")
	private String filterStr;

	@Column(name = "Remark")
	private String remark;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;
}
