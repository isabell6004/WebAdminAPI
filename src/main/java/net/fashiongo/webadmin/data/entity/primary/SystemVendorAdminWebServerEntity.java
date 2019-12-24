package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "System_VendorAdminWebServer")
@Getter
@Setter
public class SystemVendorAdminWebServerEntity {
    @Id
    @Column(name = "WebServerID")
    private Integer webServerID;

    @Column(name = "Url")
    private String url;
}
