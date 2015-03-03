<div class="span4">
	<ul class="list-group">
		<c:forEach var="genre" items="${GENRES}" varStatus="status">
			<a href="#"><li class="list-group-item">${genre.name}</li></a>
		</c:forEach>
	</ul>
</div>