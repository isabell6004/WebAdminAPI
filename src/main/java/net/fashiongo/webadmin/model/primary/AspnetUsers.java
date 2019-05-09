package net.fashiongo.webadmin.model.primary;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "aspnet_Users")
public class AspnetUsers {
    @Id
    @Column(name = "UserId")
    private String userId;

    @Column(name = "ApplicationId")
    private String applicationId;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "LoweredUserName")
    private String loweredUserName;

    @Column(name = "MobileAlias")
    private String mobileAlias;

    @Column(name = "IsAnonymous")
    private Boolean isAnonymous;

    @Column(name = "LastActivityDate")
    private Date lastActivityDate;
}
