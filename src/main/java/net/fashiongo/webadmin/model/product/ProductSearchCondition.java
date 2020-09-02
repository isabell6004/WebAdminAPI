package net.fashiongo.webadmin.model.product;

import io.jsonwebtoken.lang.Collections;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ProductSearchCondition {

    private List<Include> include;

    private Integer pageSize;

    private Integer pageNumber;

    private List<Integer> productIds;

    private List<String> styleNos;

    private Integer vendorId;

    private Integer styleId;

    private Integer patternId;

    private Integer fabricId;

    private Integer lengthId;

    private Integer masterColorId;

    public enum Include {
        VENDOR, CATEGORY, VENDOR_CATEGORY, SIZE, PACK, FABRIC, MADE_IN, LABEL, STYLE, PATTERN, LENGTH, BODY_SIZE, IMAGE, CROSS_SELLING, MEMO, INVENTORY;
    }

    public String toQueryString() {
        final StringBuilder sb = new StringBuilder("?");

        if (!Collections.isEmpty(include)) {
            sb.append("include=").append(listToDelimiter(include)).append("&");
        }
        if (!Collections.isEmpty(productIds)) {
            sb.append("productIds=").append(listToDelimiter(productIds)).append("&");
        }
        if (!Collections.isEmpty(styleNos)) {
            sb.append("styleNos=").append(listToDelimiter(styleNos)).append("&");
        }
        if (Objects.nonNull(pageSize)) {
            sb.append("ps=").append(pageSize).append("&");
        }
        if (Objects.nonNull(pageNumber)) {
            sb.append("pn=").append(pageNumber).append("&");
        }
        if (Objects.nonNull(vendorId)) {
            sb.append("vendorId=").append(vendorId).append("&");
        }
        if (Objects.nonNull(styleId)) {
            sb.append("styleId=").append(styleId).append("&");
        }
        if (Objects.nonNull(patternId)) {
            sb.append("patternId=").append(patternId).append("&");
        }
        if (Objects.nonNull(fabricId)) {
            sb.append("fabricId=").append(fabricId).append("&");
        }
        if (Objects.nonNull(lengthId)) {
            sb.append("lengthId=").append(lengthId).append("&");
        }
        if (Objects.nonNull(masterColorId)) {
            sb.append("masterColorId=").append(masterColorId).append("&");
        }

        String qs = sb.toString();

        if (qs.length() == 1) {
            return "";
        }

        if (qs.endsWith("&")) {
            qs = qs.substring(0, qs.lastIndexOf("&"));
        }
        return qs;
    }

    private String listToDelimiter(List<?> collection) {
        return collection.stream().map( o -> {
            String v = String.valueOf(o);
            if (o instanceof Include) {
                v = ((Include) o).name();
            }
            return v;
        }).collect(Collectors.joining(","));

    }

//    private String listToDelimiter(List<Integer> collection) {
//        return collection.stream().map(String::valueOf).collect(Collectors.joining(","));
//    }
//
//    private String listToDelimiter(List<Include> collection) {
//        return collection.stream().map(Include::name).collect(Collectors.joining(","));
//    }
}
