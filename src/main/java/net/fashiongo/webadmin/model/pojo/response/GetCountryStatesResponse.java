package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import net.fashiongo.webadmin.model.pojo.State;

public class GetCountryStatesResponse {
	private Integer countryId;
	private List<State> states;
	
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	
	
}
