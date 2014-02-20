package com.kronos.wfc.byteman;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		default:
			break;
		}
	}

}
