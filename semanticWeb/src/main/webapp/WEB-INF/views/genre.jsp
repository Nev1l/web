<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Genre</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
	<%@ include file="panelGenres.jsp"%>
	<div class="span2"></div>
	<div class="span10">
		<a href="<c:url value="/home.do"/>"><b>Main</b></a>
		<div class="well">
			<h2>${GENRE.name}</h2>
			<%-- <h3>Description:</h3>
			<div>${GENRE.description}</div> --%>
		</div>
		<div>
			<!--  data-toggle="collapse" aria-expanded="true" -->
			<ul class="nav nav-tabs" id="tabs">
				<li class="active" id="performers" onClick="activateTab(1)"><a> Performers
				</a></li>
				<li id="albums" onClick="activateTab(2)"><a> Albums </a></li>
				<li id="tracks" onClick="activateTab(3)"><a> Tracks </a></li>
			</ul>
		</div>
		<!-- class="collapse" -->
		<div id="collapsePerformers" style="display: block;">
			<c:forEach var="entry" items="${GROUPS}" varStatus="status">
				<c:set var="group" value="${entry.value}" />
				<!-- style="margin: 25px" -->
				<div class=" artist-grid-item g">
					<div class="col-xs-6 col-md-3 cover-image"
						style="background-image: url('${group.image}');">
						<img class="cover-image-image" src="${group.image}"> <a
							href="<c:url value="/group?name=${group.name}"/>"
							class="artist-grid-fill-link full-width-overlay"></a>
						<div class="text-over-image text-over-image--block">
							<div class="text-over-image-text">
								<a href="<c:url value="/group?name=${group.name}"/>"
									class="artist-grid-item-heading-link">
									<h3>${group.name}</h3>
								</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- class="collapse" -->
		<div id="collapseAlbums" style="display: none;">
			<c:forEach var="entry" items="${GROUPS}" varStatus="status">
				<c:set var="group" value="${entry.value}" />
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value="/group?name=${group.name}"/>">${group.name}</a>
					</div>
					<div class="panel-body">
						<c:forEach var="a" items="${group.musicAlbums}" varStatus="status">
							<c:set var="album" value="${a.value}" />
							<div class="col-xs-6 col-md-3 well">
								<a
									href="<c:url value="/album?groupName=${group.name}&albumName=${album.name}"/>">${album.name}(${album.year})
									<div>
										<img width="120" height="90" src="${album.image}" />
									</div>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- class="collapse" -->
		<div id="collapseTracks" style="display: none;">
			<c:forEach var="entry" items="${GROUPS}" varStatus="status">
				<c:set var="group" value="${entry.value}" />
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value="/group?name=${GROUP.name}"/>">${group.name}</a>
					</div>
					<div class="panel-body">
						<c:forEach var="a" items="${group.musicAlbums}" varStatus="status">
							<div class="panel-heading">
								<a
									href="<c:url value="/album?groupName=${group.name}&albumName=${a.value.name}"/>">${a.value.name}</a>
							</div>
							<div class="panel-body">
								<ul class="list-group">
									<c:forEach var="music" items="${a.value.songList}"
										varStatus="status">
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
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>
