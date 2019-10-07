package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "List_EmailType")
public class ListEmailTypeEntity {

	@Id
	@Column(name = "EmailTypeID")
	private Integer emailTypeID;

	@Column(name = "EmailType")
	private String emailType;

	@Column(name = "TemplateFileName")
	private String templateFileName;
}
