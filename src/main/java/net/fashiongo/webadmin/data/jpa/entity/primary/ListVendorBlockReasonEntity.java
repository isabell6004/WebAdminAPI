package net.fashiongo.webadmin.data.jpa.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "List_VendorBlockReason")
public class ListVendorBlockReasonEntity implements Serializable {
    @Id
    @Column(name = "BlockReasonID")
    private Integer blockReasonId;

    @Column(name = "BlockReasonTitle")
    private String blockReasonTitle;

    @Column(name = "BlockReasonDetail")
    private String blockReasonDetail;

}
