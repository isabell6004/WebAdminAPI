package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.dal.IPersistent;

@Entity
@Table(name = "Map_Photo_Calendar_Model")
@Getter
@Setter
public class MapPhotoCalendarModel implements IPersistent, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ModelScheduleID")
	private Integer modelScheduleID;

	@Column(name = "CalendarID")
	private Integer calendarID;

	@Column(name = "ModelID")
	private Integer modelID;

	@Column(name = "AvailableUnit")
	private BigDecimal availableUnit;

	@Column(name = "IsDelete")
	private Boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "ModelID", referencedColumnName = "ModelID", insertable = false, updatable = false)
    private PhotoModel photoModel;

	@OneToMany
	@JoinColumn(name = "ModelScheduleID", referencedColumnName = "ModelScheduleID")
	private List<PhotoBooking> photoBooking;
}
