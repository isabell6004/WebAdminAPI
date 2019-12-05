package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorAdminLoginLogEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface VendorAdminLoginLogEntityRepositoryCustom {
    Page<VendorAdminLoginLogEntity> findVendorAdminLoginLog(Integer pageNum, Integer pageSize, Integer wholeSalerID, String userID, LocalDate date, String ipAddress, String orderBy);
}
