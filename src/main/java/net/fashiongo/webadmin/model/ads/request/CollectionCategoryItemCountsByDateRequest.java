package net.fashiongo.webadmin.model.ads.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class CollectionCategoryItemCountsByDateRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "beginDisplayOn is required")
    private LocalDate beginDisplayOn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "endDisplayOn is required")
    private LocalDate endDisplayOn;
}
