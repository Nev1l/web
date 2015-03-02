<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script
	src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.12.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link
	href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/cloud.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/artist.css" />
<%-- <script src="http://d3js.org/d3.v3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/cloud.js"></script> --%>

<div class="navbar">
	<div class="navbar-inner">
		<div class="brand">Music ontology</div>
		<ul class="nav" style="margin-left:20%">
			<li><a href="<c:url value="/home.do"/>"><b>Main</b></a></li>
			<li>
				<div class="btn-toolbar">
					<form id="search" class="relative intro-search-container"
						method="get" action="/search">
						<input type="text" name="q" size="26" class="js-search search-box">
						<input type="hidden" name="from" value="ac"> <a href="#"
							class="btn-default btn-sm"
							onClick="document.getElementById('search').submit()" style="text-decoration:none;"> <span
							class="glyphicon glyphicon-search" aria-hidden="true"></span>
							Search
						</a>
						<!--  -->
					</form>
					<!--  -->
				</div>
			</li>
		</ul>
	</div>
</div>
