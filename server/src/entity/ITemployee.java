package entity;

import java.io.Serializable;
/**
 * ITemployee class contain all fields of the IT employee
 * @author SHIRA
 *
 */
public class ITemployee implements Serializable
{
	private int employeeId;
	private String employeeName;
	private String employeeLastName;
	private String employeeMail;
	private int numOfProjects;
	private String employeePos;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getEmployeeMail() {
		return employeeMail;
	}
	public void setEmployeeMail(String employeeMail) {
		this.employeeMail = employeeMail;
	}
	public int getNumOfProjects() {
		return numOfProjects;
	}
	public void setNumOfProjects(int numOfProjects) {
		this.numOfProjects = numOfProjects;
	}
	public String getEmployeePos() {
		return employeePos;
	}
	public void setEmployeePos(String employeePos) {
		this.employeePos = employeePos;
	}
	public ITemployee(int employeeId,String employeeName,String employeeLastName,String employeeMail,int numOfProjects,String employeePos)
	{
		this.employeeId=employeeId;
		this.employeeName=employeeName;
		this.employeeLastName=employeeLastName;
		this.employeeMail=employeeMail;
		this.numOfProjects=numOfProjects;
		this.employeePos=employeePos;
	}
}
