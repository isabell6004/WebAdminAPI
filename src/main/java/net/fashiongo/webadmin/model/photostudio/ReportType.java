package net.fashiongo.webadmin.model.photostudio;

/**
 * Created by jinwoo on 2019. 4. 19..
 */
public enum ReportType {

    AllPhotoshoot(0),
    FullModelShotWomen(1),
    FullModelShotWomenPlusSize(2),
    FlatProductShotMen(3),
    FlatProductShotKids(4),
    DailySalesReport(5),
    FlatProductShotShoes(6),
    FlatProductShotHandbags(7),
    FlatProductShotAccessories(8),
    ;

    private Integer reportTypeId;

    ReportType(Integer value) {
        this.reportTypeId = value;
    }

    public int getReportTypeId() {
        return this.reportTypeId;
    }

    public static ReportType typeOf(int value) {

        ReportType returnType = null;
        switch(value) {
            case 0:
                returnType = AllPhotoshoot;
                break;
            case 1:
                returnType = FullModelShotWomen;
                break;
            case 2:
                returnType = FullModelShotWomenPlusSize;
                break;
            case 3:
                returnType = FlatProductShotMen;
                break;
            case 4:
                returnType = FlatProductShotKids;
                break;
            case 5:
                returnType = DailySalesReport;
                break;
            case 6:
                returnType = FlatProductShotShoes;
                break;
            case 7:
                returnType = FlatProductShotHandbags;
                break;
            case 8:
                returnType = FlatProductShotAccessories;
                break;
            default:
                returnType = AllPhotoshoot;
                break;
        }
        return returnType;
    }
}
