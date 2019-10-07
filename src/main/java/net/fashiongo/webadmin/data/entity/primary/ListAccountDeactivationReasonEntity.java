package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "List_AccountDeactivationReason")
public class ListAccountDeactivationReasonEntity {

	@Id
	@Column(name = "ReasonID")
	private Integer reasonID;

	@Column(name = "Reason")
	private String reason;

}
