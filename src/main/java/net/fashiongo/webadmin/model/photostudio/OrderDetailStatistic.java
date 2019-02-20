package net.fashiongo.webadmin.model.photostudio;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 12..
 */
public class OrderDetailStatistic {

    private Integer categoryId;

    private Integer styleQuentity = 0;

    private Integer additionalColorSetQuentity = 0;

    private Integer additionalColorQuentity = 0;

    private Integer movieClipsQuentity = 0;

    public OrderDetailStatistic(Integer categoryId, int styleQuentity, int colorSetQuentity, int colorQuentity, int movieQuentity) {
        this.categoryId = categoryId;
        this.styleQuentity = styleQuentity;
        this.additionalColorQuentity = colorQuentity;
        this.additionalColorSetQuentity = colorSetQuentity;
        this.movieClipsQuentity = movieQuentity;
    }

    public Integer getAdditionalColorQuentity() {
        return additionalColorQuentity;
    }

    public void setAdditionalColorQuentity(Integer additionalColorQuentity) {
        this.additionalColorQuentity = additionalColorQuentity;
    }

    public Integer getAdditionalColorSetQuentity() {
        return additionalColorSetQuentity;
    }

    public void setAdditionalColorSetQuentity(Integer additionalColorSetQuentity) {
        this.additionalColorSetQuentity = additionalColorSetQuentity;
    }

    public Integer getMovieClipsQuentity() {
        return movieClipsQuentity;
    }

    public void setMovieClipsQuentity(Integer movieClipsQuentity) {
        this.movieClipsQuentity = movieClipsQuentity;
    }

    public Integer getStyleQuentity() {
        return styleQuentity;
    }

    public void setStyleQuentity(Integer styleQuentity) {
        this.styleQuentity = styleQuentity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public static Map<Integer, OrderDetailStatistic> build(List<Tuple> tuples) {

        List<OrderDetailStatistic> results = tuples.stream().map((tuple) -> {
            return new OrderDetailStatistic(tuple.get("categoryId", Integer.class),
                    tuple.get("styleQuentity", Long.class).intValue(), tuple.get("colorSetQuentity", Long.class).intValue(),
                    tuple.get("colorQuentity", Long.class).intValue(), tuple.get("movieQuentity", Long.class).intValue());
        }).collect(Collectors.toList());

        return results.stream().collect(Collectors.toMap(order -> order.getCategoryId(), order -> order));
    }
}
