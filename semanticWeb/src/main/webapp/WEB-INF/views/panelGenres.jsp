<div class="span3">
	<ul class="list-group">
		<c:forEach var="genre" items="${GENRES}" varStatus="status">
			<a href="<c:url value="/genre?genreName=${genre.name}"/>"><li class="list-group-item">${genre.name}</li></a>
		</c:forEach>
	</ul>
</div>