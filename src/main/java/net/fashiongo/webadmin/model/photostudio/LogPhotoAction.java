package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Log_Photo_Action")
@Getter
@Setter
public class LogPhotoAction implements IPersistent, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ActionID")
	private Integer actionID;

	@Column(name = "ActionType")
	private Integer actionType;

	@Column(name = "OrderID", insertable = false, updatable = false)
	private Integer orderID;

	@Column(name = "ItemQty")
	private Integer itemQty;

	@Column(name = "DroppedBy")
	private String droppedBy;

	@JsonIgnore
	@Column(name = "CreatedOn", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOnDate;

	@Transient
	private String createdOn;
	public String getCreatedOn() {
		return createdOnDate != null ? createdOnDate.toString() : null;
	}

	@Column(name = "CreatedBy")
	private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    private PhotoOrder photoOrder;


}
