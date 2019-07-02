package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.ad.ResultGetCategoryAdCalendar2;

import java.time.LocalDate;

public interface AdProcedureRepository {

	ResultGetCategoryAdCalendar2 up_wa_GetCategoryAdCalendar2(LocalDate categoryDate);
}
