<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="panelGenres.jsp"%>
	<div class="span12">
			<c:forEach var="group" items="${GROUPS}" varStatus="status">
			<!-- item-1  item-3n-plus-1   -->
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
								<c:forEach var="genre" items="${group.genreList}"
									varStatus="status">
									<c:if test="${!status.first}">/</c:if>
									<a href="<c:url value="/genre?genreName=${genre.name}"/>">${genre.name}</a>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
	</div>
</body>
</html>
