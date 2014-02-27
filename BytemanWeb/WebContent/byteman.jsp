<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="byteman.css">
<title>Byteman Home</title>
 <script type="text/javascript">
    
        var eventSource = new EventSource("logtail");
         
        eventSource.onmessage = function(event) {
         
            document.getElementById('log').insertAdjacentHTML('beforeend', event.data);
            document.getElementById('log').insertAdjacentHTML('beforeend', '</br>');
         
    }
    </script>
</head>
<body>
	<div id="homePage">
			<div id="column1">
				<h2>Your Current rules are:</h2>
				<form class="homePageForm" method="post" action="BytemanServlet">
					<textarea class="bmTextArea" id="textarea" name="ruleDetails" wrap="off"><%=request.getAttribute("currentRules")%></textarea>
					<button name="command" value="loadRules">Load Rules</button>
					<button name="command" value="unloadRules">Unload Rules</button>
				</form>
			</div>
		<div id="column2">
			<h2>Console output: </h2>
			<form class="homePageForm" action="">
			<div id="log" class="scrollablediv">
			<%
			File logfile = (File) application.getAttribute("logfile");
			StringBuffer sb = new StringBuffer();
			if(logfile.exists()){
				BufferedReader rdr = new BufferedReader(new FileReader(logfile));
				String line;
				while((line=rdr.readLine() )!= null){
					sb.append(line).append(System.lineSeparator());
				}	
			}

			%>
			<pre><%=sb.toString() %></pre>
			</div>
			</form>
		</div>
	</div>
</body>
</html>