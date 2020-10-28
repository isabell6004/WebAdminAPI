package net.fashiongo.webadmin.data.model.bestofbest.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BestItemCategoryResponse {
    private int level;
    private boolean isBodySize;
    private int id;
    private String name;
    private List<BestItemCategoryResponse> children;
}
