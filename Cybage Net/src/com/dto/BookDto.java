package com.dto;

public class BookDto {

	private int bId;
	private String bName;
	private String bAuthor;
	private double bPrice;
	public BookDto(int bId, String bName, String bAuthor, double bPrice) {
		super();
		this.bId = bId;
		this.bName = bName;
		this.bAuthor = bAuthor;
		this.bPrice = bPrice;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getbAuthor() {
		return bAuthor;
	}
	public void setbAuthor(String bAuthor) {
		this.bAuthor = bAuthor;
	}
	public double getbPrice() {
		return bPrice;
	}
	public void setbPrice(double bPrice) {
		this.bPrice = bPrice;
	}
	@Override
	public String toString() {
		return "BookDto [bId=" + bId + ", bName=" + bName + ", bAuthor="
				+ bAuthor + ", bPrice=" + bPrice + "]";
	}
	
	
	
	
	
	
}
