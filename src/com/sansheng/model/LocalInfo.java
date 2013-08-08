package com.sansheng.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sansheng.dao.impl.LocalInfoDaoImpl;

@DatabaseTable(daoClass = LocalInfoDaoImpl.class)
public class LocalInfo {

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

}
