package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Photo_Booking")
@Getter
@Setter
public class PhotoBooking implements IPersistent, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BookingID")
	private Integer bookingID;

	@Column(name = "ModelScheduleID", insertable = false, updatable = false)
	private Integer modelScheduleID;

	@Column(name = "BookedUnit")
	private BigDecimal bookedUnit;

	@Column(name = "StatusID")
	private Integer statusID;

	@JsonIgnore
	@Column(name = "CreatedOn", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOnDate;

	@JsonIgnore
	@Transient
	private String createdOn;
	public String getCreatedOn() {
		return createdOnDate != null ? createdOnDate.toString() : null;
	}

	@JsonIgnore
	@Column(name = "CreatedBy")
	private String createdBy;

	@JsonIgnore
	@Column(name = "ModifiedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOnDate;

	@JsonIgnore
	@Transient
	private String modifiedOn;
	public String getModifiedOn() {
		return modifiedOnDate != null ? modifiedOnDate.toString() : null;
	}

	@Column(name = "ModifiedBY")
	private String modifiedBY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ModelScheduleID", referencedColumnName = "ModelScheduleID")
    private MapPhotoCalendarModel mapPhotoCalendarModel;

}