<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="span5"></div>
	<div class="span5">
	<div class="row well">
			<c:forEach var="group" items="${GROUPS}">
				<ul class="artist-grid-items clearit">
					<li
						class=" first item-1  item-2n-plus-1  item-3n-plus-1  item-4n-plus-1  artist-grid-item g">
						<div class="cover-image"
							style="background-image: url('${group.image}');">
							<img class="cover-image-image" src="${group.image}"> <a
								href="<c:url value="/group?name=${group.name}"/>"
								class="artist-grid-fill-link full-width-overlay"></a>
							<div class="text-over-image text-over-image--block">
								<div class="text-over-image-text">
									<a href="<c:url value="/group?name=${group.name}"/>"
										class="artist-grid-item-heading-link">
										<h3>${group.name}</h3>
									</a> <a href="/tag/electronic">electronic</a> <a href="/tag/dance">dance</a>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</c:forEach>
		</div>
	</div>
	<div class="span3">
	</div>
</body>
</html>
