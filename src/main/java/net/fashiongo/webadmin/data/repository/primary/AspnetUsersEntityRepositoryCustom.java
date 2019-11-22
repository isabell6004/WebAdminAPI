package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.AspnetUsersEntity;

public interface AspnetUsersEntityRepositoryCustom {
    AspnetUsersEntity findOneByUserNameAndWholeSalerGUID(String userID, String wholeSalerGUID);
    AspnetUsersEntity findOneByWholeSalerGUID(String wholeSalerGUID);
}
