package unitTests;

public class report {

	private String from;
	private String to;
	private int num;
	public report(String from,String to,int num)
	{
		this.from=from;
		this.to=to;
		this.num=num;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

}
