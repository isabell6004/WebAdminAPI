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

@Entity
@Table(name = "Customer_SocialMedia")
@Getter
@Setter
public class CustomerSocialMediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "MapID")
    private Integer mapID;

    @Column(name = "UserTypeID")
    private Integer userTypeID;

    @Column(name = "ReferenceID")
    private Integer referenceID;

    @Column(name = "SocialMediaID")
    private Integer socialMediaID;

    @Column(name = "SocialMediaUsername")
    private String socialMediaUsername;
}
