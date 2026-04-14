package com.app.rest.jaxrs.paramconverter;

public class MyDate {

	private int date;

	private int month;

	private int year;

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public MyDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyDate(int date, int month, int year) {
		super();
		this.date = date;
		this.month = month;
		this.year = year;
	}

	@Override
	public String toString() {
		return "MyDate [date=" + date + ", month=" + month + ", year=" + year + "]";
	}

}
