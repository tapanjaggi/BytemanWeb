<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="byteman.css">
<link rel="stylesheet" href="css/mymodal.css" />
<script src="js/angular/angular.js"></script>
<script src="js/angularModal.js"></script>
<title>Byteman Home</title>
<script type="text/javascript">
	var eventSource = new EventSource("logtail");

	eventSource.onmessage = function(event) {

		document.getElementById('log').insertAdjacentHTML('beforeend',
				event.data);
		document.getElementById('log').insertAdjacentHTML('beforeend', '</br>');

	};
</script>
</head>
<body ng-app='ModalDemo'>
<div class="homeLogo">Byteman console</div>
	<div id="homePage">
		<div id="column1">
			<h2>Your Current rules are:</h2>
			<div ng-controller='MyCtrl' class="homePageFormDiv">
			<form class="homePageForm" method="post" action="BytemanServlet">
				<textarea class="bmTextArea" id="textarea" name="ruleDetails" readonly="readonly"
					wrap="off"><%=request.getAttribute("currentRules")%></textarea>

				<button ng-click='toggleEditModal()' type="button">Edit
					Rules</button>
				<button ng-click="toggleNewModal()"  type="button">Add new Rule</button>
				<button name="command" value="unloadRules">Unload All Rules</button>
				</form>
				<modal-dialog show='modalShown'> <!--  <p>Modal Content Goes here<p>-->
				<div>You can modify the rule(s) in the below textbox:</div>
				<form class="homePageForm" method="post" action="BytemanServlet">
					<textarea class="bmTextArea" id="textarea" name="ruleDetails"
						wrap="off">{{rulesToBeDisplayed}}</textarea>
					<button name="command" value="loadRules">Load Rules</button>
					<button name="command" value="unloadRules">Unload Rules</button>
				</form>
				</modal-dialog>
				<modal-dialog show='editModalShown'> <!--  <p>Modal Content Goes here<p>-->
				<div>Select the rules you want to edit:</div>
				<form class="homePageForm" method="post" action="BytemanServlet">
					<div class="btmRules">
						<c:forEach var="btmRule" items="${btmRules }" varStatus="count">
							<div>
							<input type="checkbox" value="${btmRule.ruleName }" class="ruleCheckbox"
								ng-click="toggleSelection('<c:out value="${btmRule.ruleDefinition}"/>')" />
								<pre><span class="btmRule">${btmRule.ruleName }</span></pre>
							</div>
						</c:forEach>
						<button
							ng-click="toggleModal('<c:out value="${lineSeparator}"/>')"
							type="button">Submit Rules</button>
					</div>
				</form>
				</modal-dialog>
				<modal-dialog show='newModalShown'> <!--  <p>Modal Content Goes here<p>-->
				<div>Define new rule in the below textbox:</div>
				<form class="homePageForm" method="post" action="BytemanServlet">
					<textarea class="bmTextArea" id="textarea" name="ruleDetails"
						wrap="off"></textarea>
					<button name="command" value="loadRules">Load Rules</button>
				</form>
				</modal-dialog>
			</div>
		</div>
		<div id="column2">
			<h2>Console output:</h2>
				<div id="log" class="scrollablediv">
					<%
						File logfile = (File) application.getAttribute("logfile");
						StringBuffer sb = new StringBuffer();
						if (logfile.exists()) {
							BufferedReader rdr = new BufferedReader(new FileReader(logfile));
							String line;
							while ((line = rdr.readLine()) != null) {
								sb.append(line).append(System.lineSeparator());
							}
						}
					%>
					<pre><%=sb.toString()%></pre>
				</div>
		</div>
	</div>
</body>
</html>