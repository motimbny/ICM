package controllers;

import java.io.Serializable;
/**
 * The Class tablefield.
 */
public class tablefield implements Serializable
{

    /** The month. */
    private  String month;
    
    /** The day. */
    private  Integer day;
    
    /** The num. */
    private  Integer num;
 
    /**
     * Instantiates a new tablefield.
     *
     * @param month the month
     * @param days the days
     * @param num the num
     */
    public tablefield(String month, int days, int num) {
    	this.month = month;
        this.day = days;
        this.num =num;
    }

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Sets the month.
	 *
	 * @param month the new month
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day the new day
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * Gets the num.
	 *
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * Sets the num.
	 *
	 * @param num the new num
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

}
