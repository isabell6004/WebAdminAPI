package net.fashiongo.webadmin.dao.photostudio;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.photostudio.*;
import net.fashiongo.webadmin.utility.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 4. 25..
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class IntegratedPhotoStudioRepositoryTest {

    @Autowired
    private PhotoStudioProperties photoStudioProperties;

    @Autowired
    private PhotoOrderRepository photoOrderRepository;

    @Test
    public void get_photo_orders_of_wholesaler() {

        List<Integer> wholeSalers = Arrays.asList(3996, 3830);
        Map<Integer, List<PhotoOrder>> oldOrders = photoOrderRepository.getOrderOfWholeSaler(wholeSalers);

        Assert.assertNotNull(oldOrders);

        oldOrders.values().forEach(x -> {
            log.info("order : {}", x.size());
        });

    }

    @Test
    public void get_photo_order_with_detail() {
        int year = 2019;
        int month = 4;

        LocalDateTime start = DateUtils.getFirstDayOfMonthAsLocalDateTime(year, month);
        LocalDateTime end = DateUtils.getLastDayOfMonthAsLocalDateTime(year, month);

        List<PhotoOrder> photoOrders = photoOrderRepository.getOrderWithDetail(start, end);

        for (PhotoOrder order : photoOrders) {
            log.info("order id : {}, order detail size : {}", order.getOrderID(), order.getOrderDetails().size());
        }

        Assert.assertNotNull(photoOrders);
    }


    //    SELECT
//        CONVERT(datetime,CONVERT(date,CheckOutDate)) as CheckOutDate,
//        COUNT(distinct PONumber) as totalPONumbers ,
//        SUM(CASE WHEN RowNum = 1 THEN TotalAmount ELSE 0 END) as totalOrderAmount,
//        SUM(StyleQty) as totalStyles,
//        SUM(ColorQty) as totalColors,
//        SUM(ColorSetQty) as totalColorSet,
//        SUM(MovieQty) as totalMovie,
//        (select count(*) from (
//        SELECT
//        CONVERT(datetime,CONVERT(date,MIN(CheckOutDate))) as CheckOutDate,
//        COUNT(o.WholeSalerID) as vendor,
//        o.WholeSalerID
//        from Photo_Order o
//        group by o.WholeSalerID
//        having COUNT(o.WholeSalerID)=1
//                ) as tmp
//        where tmp.CheckOutDate = CONVERT(datetime,CONVERT(date,cte.CheckOutDate))
//        group by CheckOutDate) as firstTimeVendor
//    FROM cte
//    GROUP BY CONVERT(datetime,CONVERT(date,CheckOutDate));
    @Test
    public void make_daily_report_data() {

        int year = 2019;
        int month = 4;

        LocalDateTime start = DateUtils.getFirstDayOfMonthAsLocalDateTime(year, month);
        LocalDateTime end = DateUtils.getLastDayOfMonthAsLocalDateTime(year, month);

        List<PhotoOrder> photoOrders = photoOrderRepository.getOrderWithDetail(start, end);
        Map<String, List<PhotoOrder>> photoOrderMap = photoOrders.stream().collect(Collectors.groupingBy(PhotoOrder::getCheckOutDate));
        photoOrderMap.forEach((key, value) -> System.out.println(key + " : " + value));

        List<ReportDailySummaryResponse> dailyDatas = new ArrayList<>();

        for (String checkoutDate : photoOrderMap.keySet()) {
            List<PhotoOrder> orders = photoOrderMap.get(checkoutDate);
            ReportDailySummaryResponse response = ReportDailySummaryResponse.makeSummary(checkoutDate, orders);

            List<Integer> wholeSalerIds = orders.parallelStream().map(PhotoOrder::getWholeSalerID).collect(Collectors.toList());
            Map<Integer, List<PhotoOrder>> oldOrders = photoOrderRepository.getOrderOfWholeSaler(wholeSalerIds);
            response.makeOldOrdersSummary(oldOrders);

            dailyDatas.add(response);
            log.info(response.toString());
        }

        Assert.assertNotEquals(0, dailyDatas.size());
    }


//    --Month Summary
//    SELECT
//    SUM(O.TotalAmount) AS totalOrderAmount
//    , SUM(O.TotalAmount) / COUNT(DISTINCT o.OrderID) AS avgOrder


//    , SUM(CASE WHEN O.CategoryID = 1 THEN 1	ELSE 0 END) AS WomenOrders
//    ,(select count(*) from Photo_Order os where os.CategoryID = 1 and isnull(os.IsCancelledBy, 0) > 0 and  os.CheckOutDate >= @StartDate AND os.CheckOutDate < dateadd(DD, 1, @EndDate)) AS WomenCancelled
//    , SUM(CASE WHEN O.CategoryID = 1 THEN O.TotalAmount	ELSE 0 END) AS WomenAmounts

//    , SUM(CASE WHEN O.CategoryID = 2 THEN 1	ELSE 0 END) AS PlusWomenOrders
//    ,(select count(*) from Photo_Order os where os.CategoryID = 2 and isnull(os.IsCancelledBy, 0) > 0 and  os.CheckOutDate >= @StartDate AND os.CheckOutDate < dateadd(DD, 1, @EndDate)) AS PlusWomenCancelled
//    , SUM(CASE WHEN O.CategoryID = 2 THEN O.TotalAmount	ELSE 0 END) AS PlusWomenAmounts

//    , SUM(CASE WHEN O.CategoryID = 3 THEN 1	ELSE 0 END) AS MenOrders
//    ,(select count(*) from Photo_Order os where os.CategoryID = 3 and isnull(os.IsCancelledBy, 0) > 0 and  os.CheckOutDate >= @StartDate AND os.CheckOutDate < dateadd(DD, 1, @EndDate)) AS MenCancelled
//    , SUM(CASE WHEN O.CategoryID = 3 THEN O.TotalAmount	ELSE 0 END) AS MenAmounts

//    , SUM(CASE WHEN O.CategoryID = 4 THEN 1 ELSE 0 END) AS KidsOrders
//    ,(select count(*) from Photo_Order os where os.CategoryID = 4 and isnull(os.IsCancelledBy, 0) > 0 and  os.CheckOutDate >= @StartDate AND os.CheckOutDate < dateadd(DD, 1, @EndDate)) AS KidsCancelled
//    , SUM(CASE WHEN O.CategoryID = 4 THEN O.TotalAmount	ELSE 0 END) AS KidsAmounts


//    -- 2번이상 구매한 이력이 있는 경우
//    , (
//    SELECT COUNT(*)
//    FROM (
//            SELECT CONVERT(datetime, CONVERT(date, MAX(CheckOutDate))) AS CheckOutDate
//    , COUNT(o.WholeSalerID) AS vendor, o.WholeSalerID
//    FROM Photo_Order o
//    GROUP BY o.WholeSalerID
//    HAVING COUNT(o.WholeSalerID) > 1
//            ) tmp
//    WHERE tmp.CheckOutDate >= @StartDate AND tmp.CheckOutDate < dateadd(DD, 1, @EndDate)
//            ) AS returningVendor
//
//    -- frstTimeVendor
//    , (
//    SELECT COUNT(*)
//    FROM (
//            SELECT CONVERT(datetime, CONVERT(date, MIN(CheckOutDate))) AS CheckOutDate
//    , COUNT(o.WholeSalerID) AS vendor, o.WholeSalerID
//    FROM Photo_Order o
//    GROUP BY o.WholeSalerID
//    HAVING COUNT(o.WholeSalerID) = 1
//            ) tmp
//    WHERE tmp.CheckOutDate >= @StartDate AND tmp.CheckOutDate < dateadd(DD, 1, @EndDate)
//            ) AS firstTimeVendor
//
//
//    FROM Photo_Order o
//    WHERE o.CheckOutDate >= @StartDate AND o.CheckOutDate < dateadd(DD, 1, @EndDate)
//    AND isnull(O.IsCancelledBy, 0) = 0


    @Test
    public void make_monthly_report_data() {

        int year = 2019;
        int month = 4;

        LocalDateTime start = DateUtils.getFirstDayOfMonthAsLocalDateTime(year, month);
        LocalDateTime end = DateUtils.getLastDayOfMonthAsLocalDateTime(year, month);

        List<PhotoOrder> photoOrders = photoOrderRepository.getOrderWithDetail(start, end);

        ReportMonthlySummaryResponse response = ReportMonthlySummaryResponse.makeSummary(start, photoOrders);
        List<Integer> wholeSalerIds = photoOrders.parallelStream().map(PhotoOrder::getWholeSalerID).collect(Collectors.toList());
        Map<Integer, List<PhotoOrder>> oldOrders = photoOrderRepository.getOrderOfWholeSaler(wholeSalerIds);
        response.makeOldOrdersSummary(oldOrders);

        log.info(response.toString());

        Assert.assertNotNull(response);
    }

    //    SELECT
//    CONVERT(datetime, CONVERT(date, PhotoshootDate)) AS PhotoshootDate
//            , WholeSalerCompanyName
//    , SUM(TotalAmount) AS totalOrderAmount
//    FROM Photo_Order
//    WHERE PhotoshootDate >= @StartDate AND PhotoshootDate < dateadd(DD, 1, @EndDate)
//    AND (@CategoryID=0 or CategoryID = @CategoryID)
//    AND isnull(IsCancelledBy, 0) = 0
//    GROUP BY CONVERT(datetime, CONVERT(date, PhotoshootDate)), WholeSalerCompanyName
//    ORDER BY PhotoshootDate;
    @Test
    public void daily_vendor_amount_of_photo_order_by_report_type() {

        int year = 2019;
        int month = 4;
        ReportType reportType = ReportType.FlatProductShotMen;

        LocalDateTime start = DateUtils.getFirstDayOfMonthAsLocalDateTime(year, month);
        LocalDateTime end = DateUtils.getLastDayOfMonthAsLocalDateTime(year, month);

        List<PhotoOrder> photoOrders = photoOrderRepository.getOrderWithDetailByPhotoshootDate(start, end);

        List<Integer> categoryIds = photoStudioProperties.getCategoryIds(reportType);
        List<Integer> packageIds = photoStudioProperties.getPackageIds(reportType);

        List<PhotoOrder> validOrders = photoOrders.stream().filter(
                x ->
                        x.getIsCancelledBy() == null && (categoryIds.contains(x.getCategoryID()) || packageIds.contains(x.getPackageID()))
        ).collect(Collectors.toList());

        Assert.assertNotEquals(0, validOrders.size());
        log.info(validOrders.toString());
        log.info("size : {}", validOrders.size());

        Map<String, List<PhotoOrder>> photoOrderMap = validOrders.stream().collect(Collectors.groupingBy(PhotoOrder::getPhotoshootDate));
        List<ReportDailyVendorSummaryResponse> dailyVendorDatas = new ArrayList<>();
        for (String photoshootDate : photoOrderMap.keySet()) {
            List<PhotoOrder> orders = photoOrderMap.get(photoshootDate);
            Map<String, List<PhotoOrder>> photoOrderMapByWholeSaler = orders.stream().collect(Collectors.groupingBy(PhotoOrder::getWholeSalerCompanyName));

            for (String wholeSalerCompanyName : photoOrderMapByWholeSaler.keySet()) {
                ReportDailyVendorSummaryResponse response = ReportDailyVendorSummaryResponse.makeSummary(photoshootDate, wholeSalerCompanyName, photoOrderMapByWholeSaler.get(wholeSalerCompanyName));
                dailyVendorDatas.add(response);
            }
        }

        dailyVendorDatas.forEach(value -> System.out.println(value.getDate() + " " + value.getWholeSalerCompanyName() + " " + value.getTotalOrderAmount()));
    }

    //    SELECT
//    ROW_NUMBER() OVER (PARTITION BY O.OrderID ORDER BY O.PhotoshootDate) AS RowNum
//    , O.PhotoshootDate
//    , O.OrderID
//    , O.TotalUnit
//    , OD.StyleQty
//    , O.TotalAmount
//    FROM Photo_Order O
//    INNER JOIN Photo_OrderDetail od ON O.OrderID = od.OrderID
//    WHERE o.PhotoshootDate >= @StartDate AND o.PhotoshootDate < dateadd(DD, 1, @EndDate)
//    AND (@CategoryID=0 or o.CategoryID = @CategoryID)
//    AND isnull(O.IsCancelledBy, 0) = 0
//            )
//    SELECT CONVERT(datetime, CONVERT(date, PhotoshootDate)) AS PhotoshootDate
//    , COUNT(DISTINCT OrderID) AS totalOrders
//    , SUM(CASE WHEN RowNum = 1 THEN TotalUnit ELSE 0 END) AS totalUnits
//    , SUM(StyleQty) AS totalStyles
//    , SUM(CASE WHEN RowNum = 1 THEN TotalAmount ELSE 0 END) AS totalOrderAmount
//    FROM cte
//    GROUP BY CONVERT(datetime, CONVERT(date, PhotoshootDate));
    @Test
    public void daily_total_amount_by_report_type() {

        int year = 2019;
        int month = 4;
        ReportType reportType = ReportType.FlatProductShotMen;

        LocalDateTime start = DateUtils.getFirstDayOfMonthAsLocalDateTime(year, month);
        LocalDateTime end = DateUtils.getLastDayOfMonthAsLocalDateTime(year, month);

        List<PhotoOrder> photoOrders = photoOrderRepository.getOrderWithDetailByPhotoshootDate(start, end);
        List<Integer> categoryIds = photoStudioProperties.getCategoryIds(reportType);
        List<Integer> packageIds = photoStudioProperties.getPackageIds(reportType);

        List<PhotoOrder> validOrders = photoOrders.stream().filter(
                x ->
                        x.getIsCancelledBy() == null && (categoryIds.contains(x.getCategoryID()) || packageIds.contains(x.getPackageID()))
        ).collect(Collectors.toList());

        Assert.assertNotEquals(0, validOrders.size());

        Map<String, List<PhotoOrder>> photoOrderMap = validOrders.stream().collect(Collectors.groupingBy(PhotoOrder::getPhotoshootDate));
        photoOrderMap.forEach((key, value) -> System.out.println(key + " : " + value));

        List<ReportDailySummaryResponse> dailyDatas = new ArrayList<>();
        for (String photoshootDate : photoOrderMap.keySet()) {
            ReportDailySummaryResponse response = ReportDailySummaryResponse.makeSummary(photoshootDate, validOrders);
            dailyDatas.add(response);
            log.info(response.toString());
        }

        Assert.assertNotEquals(0, dailyDatas.size());
        dailyDatas.forEach(value -> System.out.println(value.getDate() + " " + value.getTotalOrderAmount()));
    }

    //    -- Month Summary
//    WITH cte (RowNum, PhotoshootDate, OrderID, TotalAmount, IsCancelledBy, TotalUnit, StyleQty, ColorQty, ColorSetQty, MovieQty, PackageID, ColorID)
//    AS (
//            SELECT
//                    ROW_NUMBER() OVER (PARTITION BY O.OrderID ORDER BY O.PhotoshootDate) AS RowNum
//    , O.PhotoshootDate
//    , O.OrderID
//    , O.TotalAmount
//    , O.IsCancelledBy
//    , O.TotalUnit
//    , OD.StyleQty
//    , OD.ColorQty
//    , OD.ColorSetQty
//    , OD.MovieQty
//    , O.PackageID
//    , O.ColorID
//    FROM Photo_Order O
//    INNER JOIN Photo_OrderDetail OD ON O.OrderID = OD.OrderID
//    WHERE o.PhotoshootDate >= @StartDate AND o.PhotoshootDate < dateadd(DD, 1, @EndDate)
//    AND (@CategoryID=0 or o.CategoryID = @CategoryID)
//    AND isnull(O.IsCancelledBy, 0) = 0
//            )
//    SELECT
//    SUM(CASE WHEN RowNum = 1 THEN TotalAmount ELSE 0 END) as totalOrderAmount,
//    SUM(CASE WHEN RowNum = 1 THEN TotalAmount ELSE 0 END)/COUNT(distinct OrderID) as avgOrder,
//    (select count(*) from Photo_Order os where isnull(os.IsCancelledBy, 0) > 0 and  os.PhotoshootDate >= @StartDate AND os.PhotoshootDate < dateadd(DD, 1, @EndDate)) AS totalCancelledOrders,
//    COUNT(distinct OrderID) as totalOrders ,
//    SUM(CASE WHEN RowNum = 1 THEN TotalUnit ELSE 0 END) as totalUnits,
//    SUM(StyleQty) as totalStyles,
//    SUM(ColorQty) as totalColors,
//    SUM(ColorSetQty) as totalColorSet,
//    SUM(MovieQty) as totalMovie,
//    SUM(CASE WHEN RowNum = 1 AND PackageID=1 THEN 1 ELSE 0 END) AS totalpackage1,
//    SUM(CASE WHEN RowNum = 1 AND PackageID=2 THEN 1 ELSE 0 END) AS totalpackage2,
//    SUM(CASE WHEN RowNum = 1 AND PackageID=3 THEN 1 ELSE 0 END) AS totalpackage3,
//    --SUM(CASE WHEN ColorID=1 THEN 1 ELSE 0 END) AS 'lightpink',
//    SUM(CASE WHEN ColorID=2 THEN 1 ELSE 0 END) AS 'lightblue',
//    SUM(CASE WHEN ColorID=3 THEN 1 ELSE 0 END) AS 'pastelyellow',
//    CONVERT(DECIMAL(18,2),COUNT(distinct OrderID) /DATEDIFF(DD,@StartDate,dateadd(DD, 1, @EndDate))) as avgOrdersDaily,
//    CONVERT(DECIMAL(18,2),SUM(StyleQty)/DATEDIFF(DD,@StartDate,dateadd(DD, 1, @EndDate))) as avgStylesDaily,
//    CONVERT(DECIMAL(18,2),SUM(CASE WHEN RowNum = 1 THEN TotalUnit ELSE 0 END)/DATEDIFF(DD,@StartDate,dateadd(DD, 1, @EndDate))) as avgUnitsDaily,
//    (
//    SELECT COUNT(*)
//    FROM (
//            SELECT CONVERT(datetime, CONVERT(date, MAX(PhotoshootDate))) AS PhotoshootDate
//    , COUNT(o.WholeSalerID) AS vendor, o.WholeSalerID
//    FROM Photo_Order o
//    where isnull(O.IsCancelledBy, 0) = 0
//    GROUP BY o.WholeSalerID
//    HAVING COUNT(o.WholeSalerID) > 1
//            ) tmp
//    WHERE tmp.PhotoshootDate >= @StartDate AND tmp.PhotoshootDate < dateadd(DD, 1, @EndDate)
//            ) AS returningVendor,
//    (
//    SELECT COUNT(*)
//    FROM (
//            SELECT CONVERT(datetime, CONVERT(date, MIN(PhotoshootDate))) AS PhotoshootDate
//    , COUNT(o.WholeSalerID) AS vendor, o.WholeSalerID
//    FROM Photo_Order o
//    where isnull(O.IsCancelledBy, 0) = 0
//    GROUP BY o.WholeSalerID
//    HAVING COUNT(o.WholeSalerID) = 1
//            ) tmp
//    WHERE tmp.PhotoshootDate >= @StartDate AND tmp.PhotoshootDate < dateadd(DD, 1, @EndDate)
//            ) AS firstTimeVendor
//    FROM cte
    @Test
    public void make_monthly_report_data_by_report_type() {

        int year = 2019;
        int month = 4;
        ReportType reportType = ReportType.FlatProductShotMen;

        LocalDateTime start = DateUtils.getFirstDayOfMonthAsLocalDateTime(year, month);
        LocalDateTime end = DateUtils.getLastDayOfMonthAsLocalDateTime(year, month);

        List<PhotoOrder> photoOrders = photoOrderRepository.getOrderWithDetailByPhotoshootDate(start, end);
        List<Integer> categoryIds = photoStudioProperties.getCategoryIds(reportType);
        List<Integer> packageIds = photoStudioProperties.getPackageIds(reportType);

        List<PhotoOrder> validOrders = photoOrders.stream().filter(
                x ->
                        x.getIsCancelledBy() == null && (categoryIds.contains(x.getCategoryID()) || packageIds.contains(x.getPackageID()))
        ).collect(Collectors.toList());

        Assert.assertNotEquals(0, validOrders.size());

        ReportMonthlySummaryResponse response = ReportMonthlySummaryResponse.makeSummary(start, validOrders);
        List<Integer> wholeSalerIds = validOrders.parallelStream().map(PhotoOrder::getWholeSalerID).collect(Collectors.toList());
        Map<Integer, List<PhotoOrder>> oldOrders = photoOrderRepository.getOrderOfWholeSaler(wholeSalerIds);
        response.makeOldOrdersSummary(oldOrders);

        log.info(response.toString());

        Assert.assertNotNull(response);
    }


}
