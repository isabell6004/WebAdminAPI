package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "List_Application")
public class ListApplicationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ApplicationID")
	@Setter(AccessLevel.NONE)
	private Integer applicationID;

	@Column(name = "ApplicationName")
	private String applicationName;
}
