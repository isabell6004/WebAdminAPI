package net.fashiongo.webadmin.data.entity.stats;

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
@Table(name = "Search")
@Getter
@Setter
public class SearchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "SearchID")
    private Integer searchID;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "SearchQuery")
    private String searchQuery;

    @Column(name = "SearchedOn")
    private LocalDateTime searchedOn;
}
