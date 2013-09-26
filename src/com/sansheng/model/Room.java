package com.sansheng.model;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-20 上午10:49:27 declare:
 */
public class Room {

	private String shopid;
	private String shopname;  
	private String shopcall;
	private String shopadds;

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getShopcall() {
		return shopcall;
	}

	public void setShopcall(String shopcall) {
		this.shopcall = shopcall;
	}

	public String getShopadds() {
		return shopadds;
	}

	public void setShopadds(String shopadds) {
		this.shopadds = shopadds;
	}

	@Override
	public String toString() {
		return "Room [shopid=" + shopid + ", shopname=" + shopname
				+ ", shopcall=" + shopcall + ", shopadds=" + shopadds + "]";
	}

	
	
}
