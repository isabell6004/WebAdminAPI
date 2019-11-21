package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "List_SocialMedia")
@Getter
@Setter
public class ListSocialMediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "SocialMediaID")
    private Integer socialMediaID;

    @Column(name = "SocialMedia")
    private String socialMedia;

    @Column(name = "ListOrder")
    private Integer listOrder;

    @Column(name = "Icon")
    private String icon;

    @Column(name = "URL")
    private String url;

    @Column(name = "Active")
    private Boolean active;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "SocialMediaID")
    private Set<CustomerSocialMediaEntity> customerSocialMediaEntities;
}
