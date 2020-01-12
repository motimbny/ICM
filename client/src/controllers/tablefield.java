package controllers;

import java.io.Serializable;

public class tablefield implements Serializable
{

    private  String month;
    private  Integer day;
    private  Integer num;
 
    public tablefield(String month, int days, int num) {
    	this.month = month;
        this.day = days;
        this.num =num;
    }

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
