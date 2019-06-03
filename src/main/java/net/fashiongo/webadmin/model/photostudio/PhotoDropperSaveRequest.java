package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDropperSaveRequest {
    private int wholeSalerId;

    private String dropperName;

    private String dropperEmail;
}
