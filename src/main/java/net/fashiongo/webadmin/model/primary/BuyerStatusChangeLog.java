package net.fashiongo.webadmin.model.primary;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "buyer_status_change_log")
public class BuyerStatusChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_survey_id")
    private long buyerSurveyId;

    @Column(name = "buyer_id")
    private int buyerId;

    @Column(name = "buyer_status_change_type_code")
    private String buyerStatusChangeTypeCode;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private String createdBy;
}
