package com.kronos.wfc.byteman.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kronos.wfc.byteman.common.BytemanConstants;
import com.kronos.wfc.byteman.domain.ProcessExecutor;
import com.kronos.wfc.byteman.domain.dto.ProcessExecutorRequest;
import com.kronos.wfc.byteman.domain.dto.ProcessExecutorResponse;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter(BytemanConstants.COMMAND);
		
		switch (command) {
		case BytemanConstants.ATTACH_COMMAND: 
			Runtime.getRuntime().exec("cmd /c C:\\Users\\tapan.jaggi\\Desktop\\byteman-download-2.1.2-bin\\byteman-download-2.1.2\\bin\\");
			break;
		case BytemanConstants.LOAD_RULES:
			ProcessExecutorRequest executorRequest = new ProcessExecutorRequest();
			executorRequest.setCommand("C:/Kronos/test.bat");
			ProcessExecutor processExecutor = new ProcessExecutor();
			ProcessExecutorResponse executorResponse = processExecutor.execute(executorRequest);
			System.out.println(executorResponse.getOutputMessage());
		default:
			break;
		}
	}

}
