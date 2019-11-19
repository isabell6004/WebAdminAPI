package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Address {
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private Boolean isCommercial;
}