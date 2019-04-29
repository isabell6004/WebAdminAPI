package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.dal.IPersistent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-29
 */
@Entity
@Table(name = "editor_pick_vendor_content")
@Getter
@Setter
public class EditorPickVendorContent implements IPersistent, Serializable {
	private static final long serialVersionUID = -6296423204197165859L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "editor_pick_vendor_content_id")
	private Integer editorPickVendorContentId;
	
	@Column(name = "editor_title")
	private String editorTitle;
	
	@Column(name = "editor_description")
	private String editorDescription;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(name = "vendor_id")
	private Integer vendorId;
	
	@Column(name = "vendor_content_id")
	private Integer vendorContentId;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_on")
	private LocalDateTime modifiedOn;

	@Column(name = "modified_by")
	private String modifiedBy;
	
    @JoinColumn(name = "vendor_id", insertable = false, updatable = false)
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Vendor vendor;
	
    @JoinColumn(name = "vendor_content_id", insertable = false, updatable = false)
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private VendorContent vendorContent;
}
