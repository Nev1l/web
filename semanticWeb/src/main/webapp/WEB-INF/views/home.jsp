<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<form class="relative intro-search-container" method="get"
		action="/search">
		<input type="text" name="q" size="26" class="js-search search-box">
		<input type="hidden" name="from" value="ac">
		<button type="submit" class="search-submit iconleft iconleft--search"
			title="Search"></button>
	</form>
	<ul class="artist-grid-items clearit">
		<li
			class=" first item-1  item-2n-plus-1  item-3n-plus-1  item-4n-plus-1  artist-grid-item g">
			<img class="cover-image-image"
			src="http://userserve-ak.last.fm/serve/500/210128/Muse.jpg" />
			<h3>Muse</h3>
		</li>

	</ul>
	ATATATATATA
	<c:forEach var="group" items="${GROUPS}">
		<div>${group.name}</div>
	</c:forEach>
</body>
</html>
