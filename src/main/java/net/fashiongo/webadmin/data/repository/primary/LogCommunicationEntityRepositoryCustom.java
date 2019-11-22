package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.LogCommunicationEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorCommunicationList;

import java.util.List;

public interface LogCommunicationEntityRepositoryCustom {

	List<LogCommunicationEntity> findAllByRetailerIdOrderByModifiedOnDesc(Integer retailerId);

	LogCommunicationEntity findOneByCommunicationID(Integer communicationID);

	List<VendorCommunicationList> findAllByRetailerIDAndIsForVendor(Integer wholeSalerID);
}
