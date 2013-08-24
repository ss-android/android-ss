package com.sansheng.model;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-18 上午10:39:13 declare:
 */
public class User {

	private String username;
	private String password;
	private int logintype;
	private String terminalinfo;

	private String userId;
	private  String  shopId;
	private String shopName;
	private String name;
	private String shopbalance;
	private String rpv;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLogintype() {
		return logintype;
	}

	public void setLogintype(int logintype) {
		this.logintype = logintype;
	}

	public String getTerminalinfo() {
		return terminalinfo;
	}

	public void setTerminalinfo(String terminalinfo) {
		this.terminalinfo = terminalinfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShopbalance() {
		return shopbalance;
	}

	public void setShopbalance(String shopbalance) {
		this.shopbalance = shopbalance;
	}

	public String getRpv() {
		return rpv;
	}

	public void setRpv(String rpv) {
		this.rpv = rpv;
	}
	
	

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", logintype=" + logintype + ", terminalinfo=" + terminalinfo
				+ ", userId=" + userId + ", shopName=" + shopName + ", name="
				+ name + ", shopbalance=" + shopbalance + ", rpv=" + rpv + "]";
	}

}
