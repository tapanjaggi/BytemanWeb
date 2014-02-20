/**
 * 
 */
package com.kronos.wfc.byteman.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.kronos.wfc.byteman.domain.dto.ProcessExecutorRequest;
import com.kronos.wfc.byteman.domain.dto.ProcessExecutorResponse;

/**
 * Executor class for executing the processes.
 * @author Himanshu.Gaur
 * 
 */
public class ProcessExecutor {

	/**
	 * Method to execute given process.
	 * This method executes the process and gives the output/error message as output.
	 * @param executorRequest
	 * @return ProcessExecutorResponse
	 * @throws IOException
	 */
	public ProcessExecutorResponse execute(
			ProcessExecutorRequest executorRequest) throws IOException {
		return execute(executorRequest.getCommand());
	}

	/**
	 * @param command
	 * @return ProcessExecutorResponse
	 * @throws IOException
	 */
	public ProcessExecutorResponse execute(String command) throws IOException {
		Process proc = Runtime.getRuntime().exec(command);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(
				proc.getErrorStream()));

		// read the output from the command
		StringBuffer outputMessage = new StringBuffer();
		String streamOutput = null;
		while ((streamOutput = stdInput.readLine()) != null) {
			outputMessage.append(streamOutput);
		}

		// read any errors from the attempted command
		StringBuffer errorMessage = new StringBuffer();
		while ((streamOutput = stdError.readLine()) != null) {
			errorMessage.append(streamOutput);
		}
		
		ProcessExecutorResponse executorResponse = new ProcessExecutorResponse();
		executorResponse.setOutputMessage(outputMessage.toString());
		if(errorMessage.length() > 0) {
			executorResponse.setErrorMessage(errorMessage.toString());
			executorResponse.setExecutionSuccessfull(false);
		}
		return executorResponse;
	}
}
