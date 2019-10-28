package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "Log_EmailSent")
public class LogEmailSentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LogID")
	@Setter(AccessLevel.NONE)
	private Integer logID;

	@Column(name = "RetailerID")
	private int retailerID;

	@Column(name = "CompanyName")
	private String companyName;

	@Column(name = "SentEmailTypeID")
	private int sentEmailTypeID;

	@Column(name = "EmailContents")
	private String emailContents;

	@Column(name = "SentOn")
	private Timestamp sentOn;

	@Column(name = "SentBy")
	private String sentBy;

	@Column(name = "ReferenceID")
	private Integer referenceID;

	@Column(name = "ReferenceText")
	private String referenceText;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SentEmailTypeID",referencedColumnName = "EmailTypeID",insertable = false,updatable = false)
	private ListEmailTypeEntity listEmailTypeEntity;
}
