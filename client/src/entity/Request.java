package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Request implements Serializable {
	private String infoSystem;
	private String currentStatus;
	private String wantedChange;
	private String reasonForRequest;
	private String comments;
	private int addDocuments;   // 1-if added, 0 if not
	private String userSubFullName;
	private String userSubposition;
	private String userSubemail;
	private String reqDate;
	private String id;

	public String getInfoSystem() {
		return infoSystem;
	}

	public void setInfoSystem(String infoSystem) {
		this.infoSystem = infoSystem;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getWantedChange() {
		return wantedChange;
	}

	public void setWantedChange(String wantedChange) {
		this.wantedChange = wantedChange;
	}

	public String getReasonForRequest() {
		return reasonForRequest;
	}

	public void setReasonForRequest(String reasonForRequest) {
		this.reasonForRequest = reasonForRequest;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getAddDocuments() {
		return addDocuments;
	}

	public void setAddDocuments(int addDocuments) {
		this.addDocuments = addDocuments;
	}

	public String getUserSubFullName() {
		return userSubFullName;
	}

	public void setUserSubFullName(String userSubFullName) {
		this.userSubFullName = userSubFullName;
	}

	public String getUserSubposition() {
		return userSubposition;
	}

	public void setUserSubposition(String userSubposition) {
		this.userSubposition = userSubposition;
	}

	public String getUserSubemail() {
		return userSubemail;
	}

	public void setUserSubemail(String userSubemail) {
		this.userSubemail = userSubemail;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
