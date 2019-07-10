package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "security.Resource")
public class SecurityResourceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ResourceID")
	@Setter(AccessLevel.NONE)
	private Integer resourceID;

	@Column(name = "ApplicationID")
	private Integer applicationID;

	@Column(name = "Name")
	private String name;

	@Column(name = "ParentID")
	private Integer parentID;

	@Column(name = "Active")
	private boolean active = true;

	@Column(name = "ResourceType")
	private String resourceType;

	@Column(name = "URL")
	private String uRL;
}
