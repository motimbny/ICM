package entity;

import java.io.Serializable;

public class extensionrequest implements Serializable
{
	private String stage;
	private String itHandler;
	private String reason;
	private int time;
	private int id;
  public extensionrequest(int id,String stage,String itHandler,String reason,int time)
  {
	  this.id=id;
	  this.stage=stage;
	  this.itHandler=itHandler;
	  this.time=time;
	  this.reason=reason;
  }
  public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getItHandler() {
		return itHandler;
	}
	public void setItHandler(String itHandler) {
		this.itHandler = itHandler;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
}
