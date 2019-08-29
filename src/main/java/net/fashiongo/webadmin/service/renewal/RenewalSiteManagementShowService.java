package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.show.ShowScheduleWithPromotionEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.show.*;
import net.fashiongo.webadmin.data.repository.primary.show.ListShowWithScheduleEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.show.ListShowSelectParameter;
import net.fashiongo.webadmin.data.repository.primary.show.ScheduleSelectParameter;
import net.fashiongo.webadmin.data.repository.primary.show.ShowScheduleWithPromotionEntityRepository;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowListParameters;
import net.fashiongo.webadmin.data.entity.primary.show.ListShowWithScheduleEntity;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowScheduleListParameters;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RenewalSiteManagementShowService {

	private final ListShowWithScheduleEntityRepository listShowWithScheduleEntityRepository;
	private final ShowScheduleWithPromotionEntityRepository showScheduleWithPromotionEntityRepository;

	@Autowired
	public RenewalSiteManagementShowService(ListShowWithScheduleEntityRepository listShowWithScheduleEntityRepository,
											ShowScheduleWithPromotionEntityRepository showScheduleWithPromotionEntityRepository) {
		this.listShowWithScheduleEntityRepository = listShowWithScheduleEntityRepository;
		this.showScheduleWithPromotionEntityRepository = showScheduleWithPromotionEntityRepository;
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

	public AdminShowScheduleListResponse getShowScheduleList(GetShowScheduleListParameters parameters) {
		ScheduleSelectParameter selectParameter = mappingParameters(parameters);
		Page<ShowScheduleWithPromotionEntity> page = showScheduleWithPromotionEntityRepository.getShowSchedules(selectParameter);

		ShowListCountResponse count = ShowListCountResponse.builder().totalCount((int) page.getTotalElements()).build();

		return AdminShowScheduleListResponse.builder()
				.countResponses(new ArrayList<>(Collections.singletonList(count)))
				.scheduleResponses(getScheduleResponsesWithRowNumber(page))
				.build();
	}

	private List<ShowScheduleResponse> getScheduleResponsesWithRowNumber(Page<ShowScheduleWithPromotionEntity> page) {
		List<ShowScheduleWithPromotionEntity> contents = page.getContent();
		long offset = page.getPageable().getOffset();
		return IntStream.range(0, contents.size())
				.mapToObj(i -> ShowScheduleResponse.convert(contents.get(i), (i + offset + 1)))
				.collect(Collectors.toList());
	}

	private ScheduleSelectParameter mappingParameters(GetShowScheduleListParameters parameters) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
		return ScheduleSelectParameter.builder()
				.pageNumber(parameters.getPageNum())
				.pageSize(parameters.getPageSize())
				.showID(parameters.getShowId())
				.showName(parameters.getShowName())
				.location(parameters.getLocation())
				.active(parameters.getActive() == null ? null : parameters.getActive() != 0)
				.fromDate(StringUtils.isEmpty(parameters.getDateFrom()) ? null : LocalDateTime.parse(parameters.getDateFrom(), formatter))
				.toDate(StringUtils.isEmpty(parameters.getDateTo()) ? null : LocalDateTime.parse(parameters.getDateTo(), formatter))
				.orderBy(parameters.getOrderBy())
				.build();
	}
}
