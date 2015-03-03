<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="panelGenres.jsp"%>
	<div class="span1"></div>
	<div class="span7">
		<div class="well">
			<h2>
				${ALBUM.name}<span style="color: black; font-size: 14px"> of
					group </span><a href="<c:url value="/group?name=${GROUP.name}"/>">${GROUP.name}</a>
			</h2>
		</div>
		<div class="well">
			<img width="320" height="240" src="${ALBUM.image}" />
			<h3>${ALBUM.year}</h3>
			<div>
				<c:forEach var="genre" items="${ALBUM.genreList}">
					<a href="<c:url value="/genre?genreName=${genre.name}"/>"><span class="label label-warning" style="margin-top: 5px;">${genre.name}</span></a>
				</c:forEach>
			</div>
			<div>
				<div>Track list:</div>
				<ul class="list-group">
					<c:forEach var="music" items="${ALBUM.songList}" varStatus="status">
						<li class="list-group-item">
							<button type="button" data-toggle="collapse"
								data-target="#collapseTrack${status.index}"
								aria-expanded="false" aria-controls="collapseTrack">+</button>
							${status.index+1}. <a href="#">${music.name}</a>
							<div class="collapse" id="collapseTrack${status.index}">
								<div class="well">
									<div>${music.text}</div>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="span3">
		<div>
			<b>Others albums of</b> <a
				href="<c:url value="/group?name=${GROUP.name}"/>">${GROUP.name}</a>
		</div>
		<div class="well">
			<c:choose>
				<c:when test="${GROUP.musicAlbums.size()>1}">
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
				</c:when>
				<c:otherwise>
				<h3 style="position:fixed;margin-top:-20px;margin-left:25px;">No more albums</h3>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>
