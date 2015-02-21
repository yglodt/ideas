<%@ include file="/WEB-INF/views/_header.jsp"%>

<%--
<ul class="breadcrumb">
	<li><a href="<c:url value='/' />"><spring:message code="home" /></a></li>
	<li class="active"><spring:message code="${messageCode}" /></li>
</ul>
--%>

<div class="row">
	<div class="col-xs-12">
		<h3>Hello</h3>
	</div>
</div>

<table class="table">
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
				<td><fmt:formatDate pattern="dd.MM.yyyy" value="${idea.dateCreated}" /></td>
				<td>${idea.userCreated}</td>
				<td>${idea.name}</td>
				<td>${idea.description}</td>
				<td><button class="btn btn-success btn-block vote" data-id="${idea.id}">Vote</button></td>
				<td><a href="<c:url value='/idea/' />${idea.id}">Edit</a></td>
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
				alert(r);
			}, "json");

			return false;

		});

	});
</script>

<%@ include file="/WEB-INF/views/_footer.jsp"%>
