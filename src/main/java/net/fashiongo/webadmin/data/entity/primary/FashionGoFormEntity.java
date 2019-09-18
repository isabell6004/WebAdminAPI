package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "FashionGo_Form")
public class FashionGoFormEntity {
	@Id
	@GeneratedValue
	@Column(name = "FashionGoFormID")
	@Setter(AccessLevel.NONE)
	private Integer fashionGoFormId;

	@Column(name = "FormName")
	private String formName;

	@Column(name = "Memo")
	private String memo;

	@Column(name = "Attachment")
	private String attachment;

	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;
}
