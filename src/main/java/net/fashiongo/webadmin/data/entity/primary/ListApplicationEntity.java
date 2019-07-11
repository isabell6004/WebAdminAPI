package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ApplicationID",referencedColumnName = "ApplicationID",insertable = false,updatable = false)
	private Set<SecurityResourceEntity> securityResource;
}
