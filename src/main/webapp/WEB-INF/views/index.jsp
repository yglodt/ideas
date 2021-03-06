<%@ include file="/WEB-INF/views/_header.jsp"%>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Date</th>
			<th>User</th>
			<th>Title</th>
			<th>Description</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ideas}" var="idea" varStatus="status">
			<tr>
				<td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${idea.dateCreated}" /></td>
				<td>${idea.userCreated}</td>
				<td>${idea.name}</td>
				<td>${fn:substring(idea.description,0,300)}&hellip;</td>
				<td><a href="<c:url value='/idea/' />${idea.id}">View</a></td>
				<td><button class="btn btn-success btn-block vote" data-id="${idea.id}">Vote (${fn:length(idea.votes)})</button></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">
	$(document).ready(function() {

		$("button.vote").click(function() {
			var id = $(this).data("id");

			$.post("<c:url value='/vote' />", {
				"id" : id,
				"${_csrf.parameterName}" : "${_csrf.token}"
			}, function(r) {
				//alert(r);
			}, "json");

			return false;

		});

	});
</script>

<%@ include file="/WEB-INF/views/_footer.jsp"%>
