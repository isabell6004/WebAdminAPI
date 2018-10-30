package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;

public class State implements Serializable{
	private String stateAbbrev;
	private String stateName;
	
	public String getStateAbbrev() {
		return stateAbbrev;
	}
	public void setStateAbbrev(String stateAbbrev) {
		this.stateAbbrev = stateAbbrev;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
}
