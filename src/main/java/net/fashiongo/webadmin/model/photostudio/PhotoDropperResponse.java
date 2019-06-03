package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PhotoDropperResponse {
    private int dropperId;

    private String dropperName;

    private String dropperEmail;

    public static PhotoDropperResponse of(PhotoDropper photoDropper) {
        return builder()
                .dropperId(photoDropper.getDropperId())
                .dropperName(photoDropper.getDropperName())
                .dropperEmail(photoDropper.getDropperEmail())
                .build();
    }
}
