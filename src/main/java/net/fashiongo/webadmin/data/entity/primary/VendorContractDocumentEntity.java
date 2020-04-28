package net.fashiongo.webadmin.data.entity.primary;

import lombok.*;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Vendor_Contract_Document")
public class VendorContractDocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "VendorContractDocumentID")
    private Integer vendorContractDocumentID;

    @Column(name = "VendorContractID")
    private int vendorContractID;

    @Column(name = "DocumentTypeID")
    private Integer documentTypeID;

    @Column(name = "FileName")
    private String fileName;

    @Column(name = "Note")
    private String note;

    @Column(name = "ReceivedBy")
    private String receivedBy;

    @Column(name = "CreatedOn")
    private Timestamp createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "FileName2")
    private String fileName2;

    @Column(name = "FileName3")
    private String fileName3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VendorContractID", referencedColumnName = "VendorContractID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private VendorContractEntity vendorContract;

    public static VendorContractDocumentEntity create(SetVendorContractDocumentParameter request, String username) {
        return builder()
                .vendorContractID(request.getVendorContractID() == null ? 0 : request.getVendorContractID())
                .documentTypeID(request.getDocumentTypeID())
                .fileName(StringUtils.isEmpty(request.getFileName()) ? "" : request.getFileName())
                .fileName2(StringUtils.isEmpty(request.getFileName2()) ? "" : request.getFileName2())
                .fileName3(StringUtils.isEmpty(request.getFileName3()) ? "" : request.getFileName3())
                .note(StringUtils.isEmpty(request.getNote()) ? "" : request.getNote())
                .receivedBy(StringUtils.isEmpty(request.getReceivedBy()) ? "" : request.getReceivedBy())
                .createdBy(username)
                .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    public void modifyEntity(SetVendorContractDocumentParameter request) {
        this.setDocumentTypeID(request.getDocumentTypeID());
        //this.setVendorContractID(request.getVendorContractID() == null ? 0 : request.getVendorContractID());
        this.setFileName(StringUtils.isEmpty(request.getFileName()) ? "" : request.getFileName());
        this.setFileName2(StringUtils.isEmpty(request.getFileName2()) ? "" : request.getFileName2());
        this.setFileName3(StringUtils.isEmpty(request.getFileName3()) ? "" : request.getFileName3());
        this.setNote(StringUtils.isEmpty(request.getNote()) ? "" : request.getNote());
        this.setReceivedBy(StringUtils.isEmpty(request.getReceivedBy()) ? "" : request.getReceivedBy());
    }
}
