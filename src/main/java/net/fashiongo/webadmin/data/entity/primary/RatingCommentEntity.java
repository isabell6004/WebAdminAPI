package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "RatingComment")
public class RatingCommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RatingCommentID")
	private Integer ratingCommentID;

	@Column(name = "ReferenceID")
	private int referenceID;

	@Column(name = "IsVendorRating")
	private boolean isVendorRating=true;

	@Column(name = "Comment")
	private String comment;

	@Column(name = "Active")
	private boolean active =true ;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;
}
