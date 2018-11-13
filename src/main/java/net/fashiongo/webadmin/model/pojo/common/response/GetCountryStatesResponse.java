package net.fashiongo.webadmin.model.pojo.common.response;

import java.io.Serializable;
import java.util.List;

import net.fashiongo.webadmin.model.pojo.common.State;
/**
 * 
 * @author DAHYE
 *
 */
public class GetCountryStatesResponse implements Serializable{
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
