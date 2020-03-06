package net.fashiongo.webadmin.data.entity.primary;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Table(name = "buyer_email_history")
public class BuyerEmailHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_email_history_id")
    private Long id;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "email")
    private String email;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private String createdBy;
    
    public static BuyerEmailHistoryEntity create(Integer buyerId, String email, String username) {

        return builder()
                .buyerId(Long.valueOf(buyerId))
                .email(email)
                .createdOn(LocalDateTime.now())
                .createdBy(username)
                .build();
    }
}
