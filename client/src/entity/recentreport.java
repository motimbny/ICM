package entity;

import java.io.Serializable;
/**
 * The Class recentreport.
 */
public class recentreport implements Serializable{
	
	/** The from. */
	private String from;
	
	/** The to. */
	private String to;
	
	/** The type. */
	private String type;
	
	/**
	 * Instantiates a new recentreport.
	 *
	 * @param f the f
	 * @param t the t
	 * @param s the s
	 */
	public recentreport(String f,String t,String s)
	{
		this.setFrom(f);
		this.setType(s);
		this.setTo(t);
	}
	
	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	
	/**
	 * Sets the from.
	 *
	 * @param from the new from
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	
	/**
	 * Sets the to.
	 *
	 * @param to the new to
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	public Boolean comper(recentreport one,recentreport two)
	{
		if(one.getFrom().equals(two.getFrom()))
			if(one.getTo().equals(two.getTo()))
				if(one.getType().equals(one.getType()))
					return true;
		return false;
	}

}
