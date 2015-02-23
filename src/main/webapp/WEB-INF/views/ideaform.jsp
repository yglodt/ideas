<%@ include file="/WEB-INF/views/_header.jsp"%>

<%--
<ul class="breadcrumb">
	<li><a href="<c:url value='/' />"><spring:message code="home" /></a></li>
	<li class="active"><spring:message code="${messageCode}" /></li>
</ul>
--%>

<h3>${empty param.id ? 'Add' : 'Edit' }&nbsp;Idea</h3>

<form:form modelAttribute="idea" method="post" action="${pageContext.request.contextPath}/idea/edit" class="form-horizontal" role="form">

	<form:hidden path="id" />

	<div class="form-group">
		<form:label path="name" class="col-sm-2 control-label">Title</form:label>
		<div class="col-sm-10">
			<form:input path="name" cssErrorClass="errors form-control" cssClass="form-control" />
		</div>
	</div>

	<div class="form-group">
		<form:label path="description" class="col-sm-2 control-label">Description</form:label>
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
