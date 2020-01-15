package entity;

import java.io.Serializable;

public class recentreport implements Serializable{
	private String from;
	private String to;
	private String type;
	public recentreport(String f,String t,String s)
	{
		this.setFrom(f);
		this.setType(s);
		this.setTo(t);
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
