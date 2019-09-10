package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsColors {
    @JsonProperty("ProductID")
    private Integer productID;

    @JsonProperty("ColorID")
    private Integer colorID;

    @JsonProperty("Color")
    private String color;
}
