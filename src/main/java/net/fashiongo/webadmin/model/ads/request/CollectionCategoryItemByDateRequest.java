package net.fashiongo.webadmin.model.ads.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class CollectionCategoryItemByDateRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "displayOn is required")
    private LocalDate displayOn;
}
