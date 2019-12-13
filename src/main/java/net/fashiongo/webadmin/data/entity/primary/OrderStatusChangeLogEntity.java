package net.fashiongo.webadmin.data.entity.primary;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Order_Status_Change_Log")
public class OrderStatusChangeLogEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "LogID") private Integer id;
	@Column(name = "OrderID") private Integer orderId;
	@Column(name = "IsAdmin") private Boolean isAdmin;
	@Column(name = "WholeSalerID") private Integer wholesalerId;
	@Column(name = "UserName") private String userName;
	@Column(name = "AccessIP") private String accessIp;
	@Column(name = "OrderStatusID") private Integer orderStatusId;
	@Column(name = "CreatedOn") private LocalDateTime createdOn;
	@Column(name = "HappenedAt") private Integer happenedAt;
	@Column(name = "ReferenceTypeID") private Integer referenceTypeId;
}
