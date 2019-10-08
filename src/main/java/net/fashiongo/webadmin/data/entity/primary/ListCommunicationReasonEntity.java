package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "List_CommunicationReason")
public class ListCommunicationReasonEntity {

	@Id
	@Column(name = "ReasonID")
	private Integer reasonID;

	@Column(name = "Reason")
	private String reason;

	@Column(name = "ParentID")
	private Integer parentID;

	@Column(name = "Active")
	private boolean active = true;
}
