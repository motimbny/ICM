package entity;

import java.io.Serializable;

/**
 * Messages class contain all fields of message .
 *
 * @author SHIRA
 */
public class Messages implements Serializable
{
	
	/** The sent by. */
	private String sentBy;
	
	/** The subject. */
	private String subject;
	
	/** The message con. */
	private String messageCon;
	
	/** The date. */
	private String date;
    
    /** The read. */
    private int read;
    
    /**
     * Instantiates a new messages.
     *
     * @param sentBy the sent by
     * @param subject the subject
     * @param messageCon the message con
     * @param date the date
     * @param read the read
     */
    public Messages(String sentBy,String subject,String messageCon,String date,int read)
    {
    	this.sentBy=sentBy;
    	this.subject=subject;
    	this.messageCon=messageCon;
    	this.date=date;
    	this.read=read;
    }
	
	/**
	 * Gets the sent by.
	 *
	 * @return the sent by
	 */
	public String getSentBy() {
		return sentBy;
	}
	
	/**
	 * Sets the sent by.
	 *
	 * @param sentBy the new sent by
	 */
	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}
	
	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * Gets the message con.
	 *
	 * @return the message con
	 */
	public String getMessageCon() {
		return messageCon;
	}
	
	/**
	 * Sets the message con.
	 *
	 * @param messageCon the new message con
	 */
	public void setMessageCon(String messageCon) {
		this.messageCon = messageCon;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Gets the read.
	 *
	 * @return the read
	 */
	public int getRead() {
		return read;
	}
	
	/**
	 * Sets the read.
	 *
	 * @param read the new read
	 */
	public void setRead(int read) {
		this.read = read;
	}
}
