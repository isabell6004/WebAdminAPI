package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.LogCommunicationEntity;

import java.util.List;

public interface LogCommunicationEntityRepositoryCustom {

	List<LogCommunicationEntity> findAllByRetailerIdOrderByModifiedOnDesc(Integer retailerId);

	LogCommunicationEntity findOneByCommunicationID(Integer communicationID);
}
