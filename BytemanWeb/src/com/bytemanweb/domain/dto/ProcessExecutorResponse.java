/**
 * 
 */
package com.bytemanweb.domain.dto;

/**
 * @author Himanshu.Gaur
 *
 */
public class ProcessExecutorResponse {

	private boolean executionSuccessfull = true;
	
	private String outputMessage;
	
	private String errorMessage;

	public boolean isExecutionSuccessfull() {
		return executionSuccessfull;
	}

	public void setExecutionSuccessfull(boolean executionSuccessfull) {
		this.executionSuccessfull = executionSuccessfull;
	}

	public String getOutputMessage() {
		return outputMessage;
	}

	public void setOutputMessage(String outputMessage) {
		this.outputMessage = outputMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
