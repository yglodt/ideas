<%@ include file="/WEB-INF/views/_header.jsp"%>

<%--
<ul class="breadcrumb">
	<li><a href="<c:url value='/' />"><spring:message code="home" /></a></li>
	<li class="active"><spring:message code="${messageCode}" /></li>
</ul>
--%>

<blockquote>
	<p>${idea.name}</p>
	<footer>
		Idea posted by ${idea.userCreated} on
		<fmt:formatDate pattern="dd.MM.yyyy - HH:mm" value="${idea.dateCreated}" />
	</footer>
</blockquote>

<div id="ideaContent">${idea.description}</div>

<h3>Comments</h3>

<table class="table">
	<thead>
		<tr>
			<th>Date</th>
			<th>User</th>
			<th>Description</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${comments}" var="c" varStatus="status">
			<tr>
				<td><fmt:formatDate pattern="dd.MM.yyyy" value="${c.dateCreated}" /></td>
				<td>${c.userCreated}</td>
				<td>${c.description}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<form:form modelAttribute="comment" method="post" action="${pageContext.request.contextPath}/idea/edit" class="form-horizontal" role="form">

	<input type="hidden" name="parent" value="${idea.id}" />

	<div class="form-group">
		<form:label path="description" class="col-sm-2 control-label">Comment</form:label>
		<div class="col-sm-10">
			<form:textarea path="description" cssErrorClass="errors form-control" cssClass="form-control" rows="10" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-xs-5">
			<button type="submit" class="btn" name="save">Save</button>
		</div>
		<%--
		<c:if test="${not empty param.id}">
			<div class="col-xs-5">
				<button type="submit" class="btn btn-danger confirm" name="delete">
					<spring:message code="remove" />
				</button>
			</div>
		</c:if>
		--%>
	</div>


</form:form>


<script type="text/javascript">
	$(document).ready(function() {
		$("#description").wysihtml5();
	});
</script>

<%@ include file="/WEB-INF/views/_footer.jsp"%>
