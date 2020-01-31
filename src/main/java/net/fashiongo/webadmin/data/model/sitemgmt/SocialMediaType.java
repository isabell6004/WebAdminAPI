package net.fashiongo.webadmin.data.model.sitemgmt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum SocialMediaType {
    Facebook(1, "https://www.facebook.com/"),
    Instagram(2, "https://www.instagram.com/"),
    Pinterest(3, "https://www.pinterest.com/"),
    Twitter(4, "https://twitter.com/"),
    Tumblr(5, "http://.tumblr.com/"),
    YouTube(6, "https://www.youtube.com/"),
    Blog(7, "");

    private int value;
    private String url;

    SocialMediaType(int value, String url) {
        this.value = value;
        this.url = url;
    }

    public int getValue() {
        return this.value;
    }

    public String getUrl() {
        return this.url;
    }

    private static Map<Integer, SocialMediaType> socialMediaTypeMap = new HashMap<>();
    static {
        for (SocialMediaType s : SocialMediaType.values()) {
            socialMediaTypeMap.put(s.getValue(), s);
        }
    }

    public static SocialMediaType get(int value) {
        return Optional.ofNullable(socialMediaTypeMap.get(value)).orElse(null);
    }
}
