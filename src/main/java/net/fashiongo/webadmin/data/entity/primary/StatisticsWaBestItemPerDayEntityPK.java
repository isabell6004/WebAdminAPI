package net.fashiongo.webadmin.data.entity.primary;


import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class StatisticsWaBestItemPerDayEntityPK implements Serializable {
    private Date startDate;
    private Integer productID;
}
