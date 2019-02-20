package net.fashiongo.webadmin.model.photostudio;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
public enum BannerType {

//    AdsCloseButton(1),
    AdsDoNotShowAgain(2),
//    AdsTryTop(3),
    AdsTryBottom(4);

    private int value;

    BannerType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
