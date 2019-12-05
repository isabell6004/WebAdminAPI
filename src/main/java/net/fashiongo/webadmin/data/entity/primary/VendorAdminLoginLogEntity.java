package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "VendorAdmin_LoginLog")
@Getter
@Setter
public class VendorAdminLoginLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "VendorAdminLoginID")
    private Integer vendorAdminLoginID;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "LoginedOn")
    private LocalDateTime loginedOn;

    @Column(name = "IPAddress")
    private String ipAddress;
}
