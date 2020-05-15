package net.fashiongo.webadmin.data.entity.primary;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "vendor_contract_history_document")
public class VendorContractHistoryDocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_contract_history_document_id")
    private Long id;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "vendor_contract_history_id")
    private Long vendorContractHistoryId;

    @Column(name = "vendor_document_type_code")
    private Integer typeCode;

    @Column(name = "file_name1")
    private String fileName1;

    @Column(name = "file_name2")
    private String fileName2;

    @Column(name = "file_name3")
    private String fileName3;

    @Column(name = "note")
    private String note;

    @Column(name = "received_user_id")
    private Integer receivedUserId;

    @Column(name = "received_user_name")
    private String receivedUsername;

    @Column(name = "created_on", updatable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdOn;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "modified_on")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    public VendorContractHistoryDocumentEntity() {
    }

    @Builder
    public VendorContractHistoryDocumentEntity(Long vendorId, Long vendorContractHistoryId, Integer typeCode, String fileName1, String fileName2, String fileName3, String note, Integer receivedUserId, String receivedUsername, LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy) {
        this.vendorId = vendorId;
        this.vendorContractHistoryId = vendorContractHistoryId;
        this.typeCode = typeCode;
        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
        this.fileName3 = fileName3;
        this.note = note;
        this.receivedUserId = receivedUserId;
        this.receivedUsername = receivedUsername;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
    }
}
