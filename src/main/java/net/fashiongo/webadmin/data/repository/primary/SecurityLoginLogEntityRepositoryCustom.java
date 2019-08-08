package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.admin.SecurityLoginLogs;

import java.time.LocalDateTime;
import java.util.List;

public interface SecurityLoginLogEntityRepositoryCustom {
    long findAllCount(LocalDateTime sDate, LocalDateTime eDate, Integer userID, String ip);

    List<SecurityLoginLogs> findAllLimitOffset(LocalDateTime sDate, LocalDateTime eDate, Integer userID, String ip, Integer pageNum, Integer pageSize, long recCnt);
}
