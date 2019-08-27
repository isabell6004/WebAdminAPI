package net.fashiongo.webadmin.data.entity.primary.show;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ShowSchedule")
@Getter
@Setter
public class ShowScheduleEntity {
	@Id
	@Column(name = "ShowScheduleID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer showScheduleId;

	@Column(name = "ShowID")
	private int showId;

	@Column(name = "BannerImage")
	private String bannerImage;

	@Column(name = "TitleImage")
	private String titleImage;

	@Column(name = "DateFrom")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateFrom;

	@Column(name = "DateTo")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateTo;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "ListOrder")
	private int listOrder;

	@Column(name = "MobileImage")
	private String mobileImage;
}
