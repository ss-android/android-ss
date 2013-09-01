package com.sansheng.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sansheng.dao.impl.LocalInfoDaoImpl;
import com.sansheng.dao.impl.UserDaoImple;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-18 上午10:39:13 declare:
 */

@DatabaseTable(daoClass = UserDaoImple.class)
public class User {

	@DatabaseField(generatedId = true)
	private int userId;

	@DatabaseField
	private String username;
	@DatabaseField
	private String password;
	@DatabaseField
	private int logintype;
	@DatabaseField
	private String terminalinfo;

	@DatabaseField
	private String shopId;
	@DatabaseField
	private String shopName;
	@DatabaseField
	private String name;
	@DatabaseField
	private String shopbalance;
	@DatabaseField
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

	 

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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
