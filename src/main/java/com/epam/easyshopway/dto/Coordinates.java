package com.epam.easyshopway.dto;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class Coordinates {
	@Column("x_start")
	private int xStart;
	@Column("y_start")
	private int yStart;
	@Column("x_end")
	private int xEnd;
	@Column("y_End")
	private int yEnd;
	public Coordinates(int xStart, int yStart, int xEnd, int yEnd) {
		super();
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}
	public int getxStart() {
		return xStart;
	}
	public void setxStart(int xStart) {
		this.xStart = xStart;
	}
	public int getyStart() {
		return yStart;
	}
	public void setyStart(int yStart) {
		this.yStart = yStart;
	}
	public int getxEnd() {
		return xEnd;
	}
	public void setxEnd(int xEnd) {
		this.xEnd = xEnd;
	}
	public int getyEnd() {
		return yEnd;
	}
	public void setyEnd(int yEnd) {
		this.yEnd = yEnd;
	}
}
