package entity;

import java.io.Serializable;

/**
 * ITemployee class contain all fields of the IT employee.
 *
 * @author SHIRA
 */
public class ITemployee implements Serializable
{
	
	/** The employee id. */
	private int employeeId;
	
	/** The employee name. */
	private String employeeName;
	
	/** The employee last name. */
	private String employeeLastName;
	
	/** The employee mail. */
	private String employeeMail;
	
	/** The num of projects. */
	private int numOfProjects;
	
	/** The employee pos. */
	private String employeePos;
	
	/**
	 * Gets the employee id.
	 *
	 * @return the employee id
	 */
	public int getEmployeeId() {
		return employeeId;
	}
	
	/**
	 * Sets the employee id.
	 *
	 * @param employeeId the new employee id
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	/**
	 * Gets the employee name.
	 *
	 * @return the employee name
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	
	/**
	 * Sets the employee name.
	 *
	 * @param employeeName the new employee name
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	/**
	 * Gets the employee last name.
	 *
	 * @return the employee last name
	 */
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	
	/**
	 * Sets the employee last name.
	 *
	 * @param employeeLastName the new employee last name
	 */
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	
	/**
	 * Gets the employee mail.
	 *
	 * @return the employee mail
	 */
	public String getEmployeeMail() {
		return employeeMail;
	}
	
	/**
	 * Sets the employee mail.
	 *
	 * @param employeeMail the new employee mail
	 */
	public void setEmployeeMail(String employeeMail) {
		this.employeeMail = employeeMail;
	}
	
	/**
	 * Gets the num of projects.
	 *
	 * @return the num of projects
	 */
	public int getNumOfProjects() {
		return numOfProjects;
	}
	
	/**
	 * Sets the num of projects.
	 *
	 * @param numOfProjects the new num of projects
	 */
	public void setNumOfProjects(int numOfProjects) {
		this.numOfProjects = numOfProjects;
	}
	
	/**
	 * Gets the employee pos.
	 *
	 * @return the employee pos
	 */
	public String getEmployeePos() {
		return employeePos;
	}
	
	/**
	 * Sets the employee pos.
	 *
	 * @param employeePos the new employee pos
	 */
	public void setEmployeePos(String employeePos) {
		this.employeePos = employeePos;
	}
	
	/**
	 * Instantiates a new i temployee.
	 *
	 * @param employeeId the employee id
	 * @param employeeName the employee name
	 * @param employeeLastName the employee last name
	 * @param employeeMail the employee mail
	 * @param numOfProjects the num of projects
	 * @param employeePos the employee pos
	 */
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
