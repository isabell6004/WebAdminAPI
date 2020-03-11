package net.fashiongo.webadmin.model.vendor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jinwoo on 2020-01-07.
 */
public enum IndustryType {
    WOMEN(1, "Women's Clothing"),
    MEN(2, "Man's Clothing"),
    CHILDREN(3, "Children's Clothing"),
    ACCESSORIES(4, "Accessories"),
    SHOES(5, "Shoes"),
    OTHERS(6, "Others"),
    HANDBAG(7, "Handbag"),
    BEAUTY(8, "Beauty"),
    LINGERIE(9, "Lingerie");

    private int value;
    private String name;

    IndustryType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    private static Map<Integer, IndustryType> industryTypeMap = new HashMap<>();
    private static Map<String, IndustryType> strIndustryTypeMap = new HashMap<>();
    static {
        for (IndustryType i : IndustryType.values()) {
            industryTypeMap.put(i.getValue(), i);
            strIndustryTypeMap.put(i.getName(), i);
        }
    }

    public static IndustryType get(int value) {
        return Optional.ofNullable(industryTypeMap.get(value)).orElse(null);
    }

    public static IndustryType get(String name) {
        return Optional.ofNullable(strIndustryTypeMap.get(name)).orElse(null);
    }
}
