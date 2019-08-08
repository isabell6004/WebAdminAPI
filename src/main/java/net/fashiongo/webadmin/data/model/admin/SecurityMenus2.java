package net.fashiongo.webadmin.data.model.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SecurityMenus2 {

	private Integer MenuID;
	private String MenuName;
	private Integer ParentID;
	private String ParentMenuName;
	private Integer ResourceID;
	private String ResourceName;

	private String RoutePath;
	private String MenuIcon;
	private Integer ApplicationID;
	private Integer ListOrder;
	private Boolean Visible;
	private Boolean Active;

}
