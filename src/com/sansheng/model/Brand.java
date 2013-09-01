package com.sansheng.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 下午2:38:15 declare:
 */
public class Brand  implements Serializable{
	private int id;
	private String brandImg;
	private String brandName;
	private List<Product> producets;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrandImg() {
		return brandImg;
	}
	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public List<Product> getProducets() {
		return producets;
	}
	public void setProducets(List<Product> producets) {
		this.producets = producets;
	}
	
	

}
