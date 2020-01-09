package entity;

import java.io.Serializable;
/**
 * Messages class contain all fields of message 
 * @author SHIRA
 *
 */
public class Messages implements Serializable
{
	private String sentBy;
	private String subject;
	private String messageCon;
	private String date;
    private int read;
    public Messages(String sentBy,String subject,String messageCon,String date,int read)
    {
    	this.sentBy=sentBy;
    	this.subject=subject;
    	this.messageCon=messageCon;
    	this.date=date;
    	this.read=read;
    }
	public String getSentBy() {
		return sentBy;
	}
	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessageCon() {
		return messageCon;
	}
	public void setMessageCon(String messageCon) {
		this.messageCon = messageCon;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
}
