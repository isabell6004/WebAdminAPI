package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Product_Image")
public class ProductImageEntity {

	@Id
	@Column(name = "ProductImageID")
	private Integer productImageID;

	@Column(name = "ProductID")
	private int productID;

	@Column(name = "ColorID")
	private Integer colorID;

	@Column(name = "ImageName")
	private String imageName;

	@Column(name = "ListOrder")
	private Integer listOrder;

	@Column(name = "Active")
	private Boolean active;
}
