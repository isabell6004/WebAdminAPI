package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "WebAdminSearchQueryUserName")
@Getter
@Setter
public class WebAdminSearchQueryUserNameEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "SearchedOn")
    private LocalDateTime searchedOn;

    @Column(name = "SearchQuery")
    private String searchQuery;

    @Column(name = "UserNameCount")
    private Integer userNameCount;
}
