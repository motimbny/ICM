package entity;

import java.io.Serializable;

public class updateRequest implements Serializable
{
	private String apprieser;
	private String executer;
	private String tester;
	private int evaluation;
	private int examination;
	private int execution;
	private int test;
	private int id;
  public updateRequest(int id,String apprieser,String executer,String tester,
		  int evaluation,int examination,int execution,int test)
  {
	  this.setId(id);
	  this.setApprieser(apprieser);
	  this.setEvaluation(evaluation);
	  this.setExamination(examination);
	  this.setExecuter(executer);
	  this.setTest(test);
	  this.setTester(tester);
	  this.setExecution(execution);
  }
public String getApprieser() {
	return apprieser;
}
public void setApprieser(String apprieser) {
	this.apprieser = apprieser;
}
public String getExecuter() {
	return executer;
}
public void setExecuter(String executer) {
	this.executer = executer;
}
public int getEvaluation() {
	return evaluation;
}
public void setEvaluation(int evaluation) {
	this.evaluation = evaluation;
}
public int getExamination() {
	return examination;
}
public void setExamination(int examination) {
	this.examination = examination;
}
public int getExecution() {
	return execution;
}
public void setExecution(int execution) {
	this.execution = execution;
}
public int getTest() {
	return test;
}
public void setTest(int test) {
	this.test = test;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTester() {
	return tester;
}
public void setTester(String tester) {
	this.tester = tester;
}

	
}
