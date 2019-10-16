package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "Map_WholeSaler_PaymentMethod")
public class MapWholeSalerPaymentMethodEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "MapID")
	private Integer mapID;

	@Column(name = "WholeSalerID")
	private int wholeSalerID;

	@Column(name = "PaymentMethodID")
	private int paymentMethodID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PaymentMethodID", referencedColumnName = "PaymentMethodID", insertable = false, updatable = false)
	private CodePaymentMethodEntity codePaymentMethod;
}
