package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.ListCommunicationReasonEntity;

import java.util.List;

public interface ListCommunicationReasonEntityRepositoryCustom {

	List<ListCommunicationReasonEntity> findAllByActive(boolean isActive);
}
