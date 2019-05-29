package net.fashiongo.webadmin.model.photostudio;

import lombok.*;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 12..
 */
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class OrderDetailStatistic {

    private Integer categoryId;

    private Integer styleQuantity = 0;

    private Integer additionalColorSetQuantity = 0;

    private Integer additionalColorQuantity = 0;

    private Integer movieClipsQuantity = 0;

    private Integer baseColorSetQuantity = 0;

    private Integer modelSwatchQuantity = 0;

    private Integer newMovieClipsQuantity = 0;

    private Integer colorSwatchQuantity = 0;

    public static Map<Integer, OrderDetailStatistic> build(List<Tuple> tuples) {

        List<OrderDetailStatistic> results = tuples.stream().map((tuple) -> {
            OrderDetailStatistic statistic = new OrderDetailStatistic().toBuilder()
                    .categoryId(tuple.get("categoryId", Integer.class))
                    .styleQuantity(Optional.ofNullable(tuple.get("styleQuantity", Long.class)).orElse(0L).intValue())
                    .additionalColorQuantity(Optional.ofNullable(tuple.get("colorQuantity", Long.class)).orElse(0L).intValue())
                    .additionalColorSetQuantity(Optional.ofNullable(tuple.get("colorSetQuantity", Long.class)).orElse(0L).intValue())
                    .movieClipsQuantity(Optional.ofNullable(tuple.get("movieQuantity", Long.class)).orElse(0L).intValue())
                    .baseColorSetQuantity(Optional.ofNullable(tuple.get("baseColorSetQuantity", Long.class)).orElse(0L).intValue())
                    .modelSwatchQuantity(Optional.ofNullable(tuple.get("modelSwatchQuantity", Long.class)).orElse(0L).intValue())
                    .newMovieClipsQuantity(Optional.ofNullable(tuple.get("movieClipQuantity", Long.class)).orElse(0L).intValue())
                    .colorSwatchQuantity(Optional.ofNullable(tuple.get("colorSwatchQuantity", Long.class)).orElse(0L).intValue())
                    .build();
            return statistic;
        }).collect(Collectors.toList());

        return results.stream().collect(Collectors.toMap(order -> order.getCategoryId(), order -> order));
    }
}
