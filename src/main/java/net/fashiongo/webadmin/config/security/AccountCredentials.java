/**
 * 
 */
package net.fashiongo.webadmin.config.security;

/**
 * @author kcha
 *
 */
public class AccountCredentials {
	private String username;
	private String pwd;
	private String accesscode;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAccesscode() {
		return accesscode;
	}
	public void setAccesscode(String accesscode) {
		this.accesscode = accesscode;
	}
}
