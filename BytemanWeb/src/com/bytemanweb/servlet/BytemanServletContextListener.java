package com.bytemanweb.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bytemanweb.common.BytemanConstants;
import com.bytemanweb.domain.ProcessExecutor;
import com.bytemanweb.domain.dto.LogTailerListener;
import com.bytemanweb.domain.dto.ProcessExecutorResponse;

/**
 * Application Lifecycle Listener implementation class BytemanServletContextListener
 *
 */
public class BytemanServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public BytemanServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent ctxEvent) {
    	ServletContext ctx = ctxEvent.getServletContext();
        String bytemanHome = ctx.getRealPath("/utils");
        System.setProperty("BYTEMAN_HOME", bytemanHome);
        System.out.println("Byteman Home is " +System.getProperty("BYTEMAN_HOME"));
        
        String processId = getCurrentJavaProcessId();
        ProcessExecutor pe = new ProcessExecutor();
        try {
			pe.execute(bytemanHome+File.separator+BytemanConstants.INSTALL_SCRIPT+" "+ processId);
			File logFile = new File(BytemanConstants.TEMP_DIR+ File.separator+ BytemanConstants.LOG_FILE_NAME );
			if(logFile.exists()){
				logFile.delete();
			}
			logFile.createNewFile();
			logFile.deleteOnExit();
			
			String ruleSubmit = bytemanHome+File.separator+BytemanConstants.RULE_SUBMIT_SCRIPT+" "+ bytemanHome +  File.separator+BytemanConstants.RULES_DIR+ File.separator+ BytemanConstants.OPEN_LOG_RULE_FILE;
			ProcessExecutorResponse res = pe.execute(ruleSubmit);
			
			LogTailerListener.startLogTailer(logFile.getAbsolutePath());
			
			System.out.println("submitted rule " +res.getOutputMessage());
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @return
	 */
	private String getCurrentJavaProcessId() {
		String name = ManagementFactory.getRuntimeMXBean().getName();
        int index = name.indexOf("@");
        String processId = name.substring(0,index);
        System.out.println("Process ID : "+processId);
		return processId;
	}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent ctxEvent) {
        // TODO Auto-generated method stub
    }
	
}
