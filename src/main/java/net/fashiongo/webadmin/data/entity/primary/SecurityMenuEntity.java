package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "[security.Menu]")
public class SecurityMenuEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MenuID")
	@Setter(AccessLevel.NONE)
	private Integer menuID;

	@Column(name = "Name")
	private String name;

	@Column(name = "ParentID")
	private Integer parentID;

	@Column(name = "ApplicationID")
	private int applicationID;

	@Column(name = "ResourceID")
	private Integer resourceID;

	@Column(name = "Visible")
	private boolean visible = true;

	@Column(name = "Active")
	private boolean active = true;

	@Column(name = "ListOrder")
	private Integer listOrder;

	@Column(name = "RoutePath")
	private String routePath;

	@Column(name = "MenuIcon")
	private String menuIcon;

    @OneToOne
    @JoinColumn(name = "ResourceID", updatable = false, insertable = false)
    private SecurityPermissionEntity securityPermissionEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ResourceID", updatable = false, insertable = false)
    private SecurityResourceEntity securityResourceEntity;
}
