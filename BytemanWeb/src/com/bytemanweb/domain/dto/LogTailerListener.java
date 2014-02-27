package com.bytemanweb.domain.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

import com.bytemanweb.common.BytemanConstants;

public class LogTailerListener extends TailerListenerAdapter {
	
	private static Vector<String> logs = new Vector<>() ;

    public void handle(String line) {
    	logs.add(line);
    }
    
    public static void startLogTailer(String filePath){
    	TailerListener listener = new LogTailerListener();
    	System.out.println("starting listener for : "+filePath);
    	File file = new File(filePath);
		  Tailer tailer = new Tailer(file, listener, BytemanConstants.LOG_POLL_PERIOD);
	      Thread thread = new Thread(tailer);
	      thread.setDaemon(true);
	      thread.start();
    }
    
    public static List<String> getLogs(){
    	List<String> logsTillNow;
    	synchronized (logs) {
			logsTillNow = new ArrayList<>(logs);
			clearLogs();
		}
		return logsTillNow;
    }

    private static void clearLogs(){
    	if(logs.size() > 0){
    		logs.clear();
    	}
    }
}
