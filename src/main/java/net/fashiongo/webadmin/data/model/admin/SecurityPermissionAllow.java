package net.fashiongo.webadmin.data.model.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SecurityPermissionAllow {
    private Integer resourceID;

    private Integer allow;

    private Integer allowEdit;

    private Integer allowDelete;

    private Integer allowAdd;
}
