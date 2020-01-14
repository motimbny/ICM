package entity;

public class recentreport {
	
	private String from;
	private String to;
	private String stage;
	public recentreport(String f,String t,String s)
	{
		this.setFrom(f);
		this.setStage(s);
		this.setTo(t);
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

}
