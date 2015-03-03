<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="span5">
	</div>
	<div class="span7">
		<div class="well">
			<h2>
				${ARTIST.name}<span style="color: black; font-size: 14px"> is
					member of </span><a href="<c:url value="/group?name=${GROUP.name}"/>">${GROUP.name}</a>
			</h2>
		</div>
		<div class="well">
			<img width="320" height="240" src="${ARTIST.image}" />
		</div>
		<div>
			<h3>Description:</h3>
			<div>${ARTIST.description}</div>
		</div>
	</div>
	<div class="span3">
		<div>
			<b>Others members of</b> <a href="/group?name=${GROUP.name}">${GROUP.name}</a>
		</div>
		<div class="well">
			<ul>
				<c:forEach var="entry" items="${GROUP.musicArtistList}"
					varStatus="status">
					<c:set var="artist" value="${entry.value}" />
					<c:if test="${artist.name ne ARTIST.name}">
					<li><a
						href="<c:url value="/artist?groupName=${GROUP.name}&artistName=${artist.name}"/>">${artist.name}
							<div class="well">
								<img width="75" height="60" src="${artist.image}" />
							</div>
					</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
