<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="panelGenres.jsp"%>
	<div class="span2"></div>
	<div class="span7">
		<a href="<c:url value="/home.do"/>"><b>All group</b></a>
		<div class="well">
			<h2>${GROUP.name}</h2>
			<img width="320" height="240" src="${GROUP.image}">
		</div>
		<div>
			<c:forEach var="genre" items="${GROUP.genreList}">
				<a href="<c:url value="/genre?genreName=${genre.name}"/>"><span class="label label-warning" style="margin-top: 5px;">${genre.name}</span></a>
			</c:forEach>
		</div>
		<a data-toggle="collapse" href="#collapseMembers"
			aria-expanded="false" aria-controls="collapseMembers"> Members </a>
		<div class="collapse" id="collapseMembers">
			<ul>
				<c:forEach var="entry" items="${GROUP.musicArtistList}"
					varStatus="status">
					<c:set var="artist" value="${entry.value}" />
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
		<div>
			<h3>Description:</h3>
			<div>${GROUP.description}</div>
		</div>
	</div>
	<div class="span3">
		<div>
			<b>Albums of </b><a href="<c:url value="/group?name=${GROUP.name}"/>">${GROUP.name}</a>
		</div>
		<div class="well">
			<ul>
				<c:forEach var="entry" items="${GROUP.musicAlbums}"
					varStatus="status">
					<c:set var="album" value="${entry.value}" />
					<li><a
						href="<c:url value="/album?groupName=${GROUP.name}&albumName=${album.name}"/>">${album.name}(${album.year})
							<div class="well">
								<img width="75" height="60" src="${album.image}" />
							</div>
					</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
