package net.fashiongo.webadmin.data.entity.primary.show;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "List_Show")
@Getter
@Setter
public class ListShowWithScheduleEntity {
	@Id
	@Column(name = "ShowID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer showId;

	@Column(name = "ShowName")
	private String showName;

	@Column(name = "Location")
	private String location;

	@Column(name = "Url")
	private String url;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "LogoFileName")
	private String logoFileName;

	@Column(name = "ShowCode")
	private String showCode;

	@Column(name = "Active_Register")
	private Boolean activeRegister;

	@OneToMany
	@JoinColumn(name = "ShowID", referencedColumnName = "ShowID")
	private List<ShowScheduleEntity> showSchedule;
}
