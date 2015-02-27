<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<c:forEach var="group" items="${GROUPS}">
		<div>${group.name}</div>
	</c:forEach>
</body>
</html>
