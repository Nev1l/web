<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="span7">
		<div class="well">
			<h2>${GROUP.name}</h2>
			<img width="320" height="240" src="${GROUP.image}">
		</div>
		<a data-toggle="collapse" href="#collapseMembers"
			aria-expanded="false" aria-controls="collapseMembers"> Members: </a>
		<div class="collapse" id="collapseMembers">
			<ul>
				<c:forEach var="entry" items="${GROUP.musicArtistList}"
					varStatus="status">
					<c:set var="artist" value="${entry.value}"/>
					<li><button type="button" data-toggle="collapse"
							data-target="#collapse${status.index}" aria-expanded="false"
							aria-controls="collapseExample">+</button> <a
						href="<c:url value="/artist?groupName=${GROUP.name}&artistName=${artist.name}"/>">${artist.name}</a>
						<div class="collapse" id="collapse${status.index}">
							<div class="well">
								<img width="320" height="240" src="${artist.image}" />
							</div>
						</div></li>
				</c:forEach>
			</ul>
		</div>
		<div>Genres labels:</div>
		<div>
			<h3>Description:</h3>
			<div>${GROUP.description}</div>
		</div>
		<div>Albums:</div>
	</div>
</body>
</html>
