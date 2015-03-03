<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="span5"></div>
	<div class="span7">
		<div class="well">
			<h2>
				${ALBUM.name}<span style="color: black; font-size: 14px"> of
					group </span><a href="<c:url value="/group?name=${GROUP.name}"/>">${GROUP.name}</a>
			</h2>
		</div>
		<div class="well">
			<img width="320" height="240" src="${ALBUM.image}" />
			<div>Genres labels</div>
			<h3>${ALBUM.year}</h3>
			<div>
				<div>Track list:</div>
				<ol>
					<c:forEach var="music" items="${ALBUM.songList}" varStatus="status">
						<li><button type="button" data-toggle="collapse"
								data-target="#collapseTrack${status.index}"
								aria-expanded="false" aria-controls="collapseTrack">+</button>
							<a	href="#">${music.name}</a>
							<div class="collapse" id="collapseTrack${status.index}">
								<div class="well">
									<div>${music.text}</div>
								</div>
							</div></li>
					</c:forEach>
				</ol>
			</div>
		</div>
	</div>
	<div class="span3">
		<div>
			<b>Others albums of</b> <a href="/group?name=${GROUP.name}">${GROUP.name}</a>
		</div>
		<div class="well">
			<ul>
				<c:forEach var="entry" items="${GROUP.musicAlbums}"
					varStatus="status">
					<c:set var="album" value="${entry.value}" />
					<c:if test="${album.name ne ALBUM.name}">
						<li><a
							href="<c:url value="/album?groupName=${GROUP.name}&albumName=${album.name}"/>">${album.name}(${album.year})
								<div class="well">
									<img width="75" height="60" src="${album.image}" />
								</div>
						</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
