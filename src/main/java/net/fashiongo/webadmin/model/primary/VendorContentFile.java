package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.dal.IPersistent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-15
 */
@Entity
@Table(name = "Vendor_Content_File")
@Getter
@Setter
public class VendorContentFile implements IPersistent, Serializable {
	private static final long serialVersionUID = 6637595841790475837L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VendorContentFileID")
	private Integer vendorContentFileId;
	
	@Column(name = "VendorContentID")
	private Integer vendorContentId;
	
	@Column(name = "File_Type")
	private Integer fileType;
	
	@Column(name = "File_Name")
	private String fileName;
	
	@Column(name = "ListOrder")
	private Integer listOrder;
	
}
