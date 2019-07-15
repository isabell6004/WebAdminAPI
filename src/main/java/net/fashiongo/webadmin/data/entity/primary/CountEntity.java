package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "[Count]")
public class CountEntity {

	@Id
	@Column(name = "CountID")
	private Integer countID;

	@Column(name = "CountTypeID")
	private int countTypeID;

	@Column(name = "EntityID")
	private int entityID;

	@Column(name = "ReferenceID")
	private Integer referenceID;

	@Column(name = "Count")
	private int count;
}
