package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SecurityAccessCodeEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface SecurityAccessCodeEntityRepositoryCustom {
    List<SecurityAccessCodeEntity> findAllAccessCode(String accessCode, LocalDateTime startDate, LocalDateTime endDate);
}
