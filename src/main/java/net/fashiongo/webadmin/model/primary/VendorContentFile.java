package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-15
 */
@Getter
@Data
@Entity
@Table(name = "Vendor_Content_File")
public class VendorContentFile {
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