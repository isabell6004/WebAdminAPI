package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.ad.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AdProcedureRepository {

	ResultGetCategoryAdCalendar2 up_wa_GetCategoryAdCalendar2(LocalDate categoryDate);

	ResultGetCategoryAdDetail up_wa_GetCategoryAdDetail(LocalDate categoryDate, int spotId);

    ResultGetCategoryAdList up_wa_GetCategoryAdList(LocalDate categoryDate);
}
