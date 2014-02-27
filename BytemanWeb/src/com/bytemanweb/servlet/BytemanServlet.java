package com.bytemanweb.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytemanweb.common.Byteman;
import com.bytemanweb.common.BytemanConstants;
import com.bytemanweb.domain.ProcessExecutor;
import com.bytemanweb.domain.dto.ProcessExecutorRequest;
import com.bytemanweb.domain.dto.ProcessExecutorResponse;

/**
 * Servlet implementation class BytemanServlet
 */
public class BytemanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BytemanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProcessExecutor processExecutor = new ProcessExecutor();
		ProcessExecutorRequest executorRequest = new ProcessExecutorRequest();
        String bytemanHome = request.getServletContext().getRealPath("/utils");
		String submitCommand = bytemanHome+File.separator+BytemanConstants.RULE_DISLAY_SCRIPT;
		executorRequest.setCommand(submitCommand);
		ProcessExecutorResponse executorResponse = processExecutor.execute(executorRequest);
		request.setAttribute("currentRules", Byteman.getRulesInDisplayFormat(executorResponse.getOutputMessage()));
		request.getRequestDispatcher("byteman.jsp").forward(request, response);
		//System.out.println(executorResponse.getOutputMessage());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter(BytemanConstants.COMMAND);
		
		switch (command) {
		case BytemanConstants.LOAD_RULES:
			String ruleDetails = request.getParameter("ruleDetails");
			Byteman.writeToRulesFile(ruleDetails);
			ProcessExecutorRequest executorRequest = new ProcessExecutorRequest();
	        String bytemanHome = request.getServletContext().getRealPath("/utils");
			String submitCommand = bytemanHome+File.separator+BytemanConstants.RULE_SUBMIT_SCRIPT + " " + BytemanConstants.RULES_LOAD_TEMP_FILE;
			executorRequest.setCommand(submitCommand);
			ProcessExecutor processExecutor = new ProcessExecutor();
			ProcessExecutorResponse executorResponse = processExecutor.execute(executorRequest);
			System.out.println(executorResponse.getOutputMessage());
			break;
		case BytemanConstants.UNLOAD_RULES:
			ruleDetails = request.getParameter("ruleDetails");
			Byteman.writeToRulesFile(ruleDetails);
			executorRequest = new ProcessExecutorRequest();
	        bytemanHome = request.getServletContext().getRealPath("/utils");
			submitCommand = bytemanHome+File.separator+BytemanConstants.RULE_UNLOAD_SCRIPT + " " + BytemanConstants.RULES_LOAD_TEMP_FILE;
			executorRequest.setCommand(submitCommand);
			processExecutor = new ProcessExecutor();
			executorResponse = processExecutor.execute(executorRequest);
			System.out.println(executorResponse.getOutputMessage());
			break;
		default:
			break;
		}
	}

}
