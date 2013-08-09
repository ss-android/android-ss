package com.sansheng.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sansheng.dao.impl.LocalInfoDaoImpl;

@DatabaseTable(daoClass = LocalInfoDaoImpl.class)
public class LocalInfo {

	public enum InfoType {
		announce, news, sales, introduce, history, honor, culture, brand, chariman, industry, world,
	}

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String title;
	@DatabaseField
	private String SubTitle;
	@DatabaseField
	private String Content;

	@DatabaseField
	private String data;

	@DatabaseField
	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return SubTitle;
	}

	public void setSubTitle(String subTitle) {
		SubTitle = subTitle;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getType() {
		return type;
	}

	public void setType(InfoType infoType) {
		type = getInfoType(infoType);
	}

	public static int getInfoType(InfoType infoType) {
		int type = 0;
		if (infoType == InfoType.announce) {
			type = 0;
		} else if (infoType == InfoType.news) {
			type = 1;
		} else if (infoType == InfoType.sales) {
			type = 2;
		} else if (infoType == InfoType.introduce) {
			type = 3;
		} else if (infoType == InfoType.history) {
			type = 4;
		} else if (infoType == InfoType.honor) {
			type = 5;
		} else if (infoType == InfoType.culture) {
			type = 6;
		} else if (infoType == InfoType.brand) {
			type = 7;
		} else if (infoType == InfoType.chariman) {
			type = 8;
		} else if (infoType == InfoType.industry) {
			type = 9;
		} else if (infoType == InfoType.world) {
			type = 10;
		}

		return type;
	}

}
