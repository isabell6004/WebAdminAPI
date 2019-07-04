package net.fashiongo.webadmin.data.model.ad;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResultGetCategoryAdDetail {

	private List<Bidding2> bidding2List;

	private List<CuratedBest> curatedBestList;
}
