<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="byteman.css">
<title>Byteman Home</title>
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
			<textarea class="bmTextArea"></textarea>
			</form>
		</div>
	</div>
</body>
</html>