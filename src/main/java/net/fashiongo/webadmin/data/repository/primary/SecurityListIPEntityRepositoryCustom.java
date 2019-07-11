package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.admin.SecurityListIP;

import java.util.List;

public interface SecurityListIPEntityRepositoryCustom {
    List<SecurityListIP> findAllOrderByIPID();
}
