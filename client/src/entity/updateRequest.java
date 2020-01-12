package entity;

import java.io.Serializable;

/**
 * updateRequest contain all fields of update request.
 *
 * @author SHIRA
 */
public class updateRequest implements Serializable {

	/** The apprieser. */
	private String apprieser;

	/** The executer. */
	private String executer;

	/** The tester. */
	private String tester;

	/** The evaluation. */
	private int evaluation;

	/** The examination. */
	private int examination;

	/** The execution. */
	private int execution;

	/** The test. */
	private int test;

	/** The id. */
	private int id;

	/**
	 * Instantiates a new update request.
	 *
	 * @param id          the id
	 * @param apprieser   the apprieser
	 * @param executer    the executer
	 * @param tester      the tester
	 * @param evaluation  the evaluation
	 * @param examination the examination
	 * @param execution   the execution
	 * @param test        the test
	 */
	public updateRequest(int id, String apprieser, String executer, String tester, int evaluation, int examination,
			int execution, int test) {
		this.setId(id);
		this.setApprieser(apprieser);
		this.setEvaluation(evaluation);
		this.setExamination(examination);
		this.setExecuter(executer);
		this.setTest(test);
		this.setTester(tester);
		this.setExecution(execution);
	}

	/**
	 * Gets the apprieser.
	 *
	 * @return the apprieser
	 */
	public String getApprieser() {
		return apprieser;
	}

	/**
	 * Sets the apprieser.
	 *
	 * @param apprieser the new apprieser
	 */
	public void setApprieser(String apprieser) {
		this.apprieser = apprieser;
	}

	/**
	 * Gets the executer.
	 *
	 * @return the executer
	 */
	public String getExecuter() {
		return executer;
	}

	/**
	 * Sets the executer.
	 *
	 * @param executer the new executer
	 */
	public void setExecuter(String executer) {
		this.executer = executer;
	}

	/**
	 * Gets the evaluation.
	 *
	 * @return the evaluation
	 */
	public int getEvaluation() {
		return evaluation;
	}

	/**
	 * Sets the evaluation.
	 *
	 * @param evaluation the new evaluation
	 */
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	/**
	 * Gets the examination.
	 *
	 * @return the examination
	 */
	public int getExamination() {
		return examination;
	}

	/**
	 * Sets the examination.
	 *
	 * @param examination the new examination
	 */
	public void setExamination(int examination) {
		this.examination = examination;
	}

	/**
	 * Gets the execution.
	 *
	 * @return the execution
	 */
	public int getExecution() {
		return execution;
	}

	/**
	 * Sets the execution.
	 *
	 * @param execution the new execution
	 */
	public void setExecution(int execution) {
		this.execution = execution;
	}

	/**
	 * Gets the test.
	 *
	 * @return the test
	 */
	public int getTest() {
		return test;
	}

	/**
	 * Sets the test.
	 *
	 * @param test the new test
	 */
	public void setTest(int test) {
		this.test = test;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the tester.
	 *
	 * @return the tester
	 */
	public String getTester() {
		return tester;
	}

	/**
	 * Sets the tester.
	 *
	 * @param tester the new tester
	 */
	public void setTester(String tester) {
		this.tester = tester;
	}

}
