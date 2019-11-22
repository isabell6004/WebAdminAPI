package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CodeVendorCapTypeEntity;

import java.util.List;

public interface CodeVendorCapTypeEntityRepositoryCustom {
    List<CodeVendorCapTypeEntity> findVendorCapDefault();
}
