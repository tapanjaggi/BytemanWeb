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
<div id="column1-wrap">
    <div id="column1">
    <h2>Your Current rules are: </h2>
    <form method="post" action="BytemanServlet">
    <textarea id="textarea" rows="35"></textarea>
    <button name="submit" value="load">
Load Rules</button>
    <button name="submit" value="unload">
Unload Rules</button>
    </form>
</div>
</div>
<div id="column2">column2</div>

</body>
</html>