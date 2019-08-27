package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.repository.primary.show.ListShowWithScheduleEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.show.ListShowSelectParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowListParameters;
import net.fashiongo.webadmin.data.model.sitemgmt.show.ListShowResponse;
import net.fashiongo.webadmin.data.model.sitemgmt.show.AdminShowListResponse;
import net.fashiongo.webadmin.data.model.sitemgmt.show.ShowListCountResponse;
import net.fashiongo.webadmin.data.entity.primary.show.ListShowWithScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RenewalSiteManagementShowService {

	private final ListShowWithScheduleEntityRepository listShowWithScheduleEntityRepository;

	@Autowired
	public RenewalSiteManagementShowService(ListShowWithScheduleEntityRepository listShowWithScheduleEntityRepository) {
		this.listShowWithScheduleEntityRepository = listShowWithScheduleEntityRepository;
	}

	public AdminShowListResponse getShowList(GetShowListParameters parameters) {
		ListShowSelectParameter param = mappingParameters(parameters);

		Page<ListShowWithScheduleEntity> page = listShowWithScheduleEntityRepository.getShowList(param);
		ShowListCountResponse count = ShowListCountResponse.builder().recCnt((int) page.getTotalElements()).build();
		return AdminShowListResponse.builder()
				.showList(getListShowResponsesWithRowNumber(page))
				.countResponses(new ArrayList<>(Collections.singletonList(count)))
				.build();
	}

	private ListShowSelectParameter mappingParameters(GetShowListParameters parameters) {
		return ListShowSelectParameter.builder()
					.pageNum(parameters.getPageNum())
					.pageSize(parameters.getPageSize())
					.active(parameters.getActive() == null ? null : parameters.getActive() != 0)
					.showName(parameters.getShowName())
					.location(parameters.getLocation())
					.orderBy(parameters.getOrderBy())
					.build();
	}

	private List<ListShowResponse> getListShowResponsesWithRowNumber(Page<ListShowWithScheduleEntity> page) {
		List<ListShowWithScheduleEntity> entities = page.getContent();
		long offset = page.getPageable().getOffset();
		return IntStream.range(0, entities.size())
				.mapToObj(i -> ListShowResponse.convertFrom(entities.get(i), (i + offset + 1)))
				.collect(Collectors.toList());
	}
}
