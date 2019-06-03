package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PhotoOrderSiblingResponse {
    private String prevPoNumber;
    private String nextPoNumber;
}
