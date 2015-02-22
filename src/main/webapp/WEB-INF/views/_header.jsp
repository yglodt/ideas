<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Ideas</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript">
	var shBaseUrl = "<c:url value='/' />";
</script>
<c:choose>
	<%--<c:when test="${not fn:contains(pageContext.request.serverName, 'localhost') and not pageContext.request.remoteAddr == '192.168.136.105' and empty param.nocombine}">--%>
	<c:when test="${not fn:contains(pageContext.request.serverName, 'localhost') and empty param.nocombine}">
		<link href="${pageContext.request.contextPath}/resources/css/$dynamicResourceNamePart$.min.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/resources/js/$dynamicResourceNamePart$.min.js" type="text/javascript"></script>
	</c:when>
	<c:otherwise>
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
	</c:otherwise>
</c:choose>
</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top hidden-print" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value='/' />">All Ideas</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav" id="shNav">
				<li><a href="<c:url value='/ideas/highesvotes' />">Most wanted Ideas</a></li>
				<li><a href="<c:url value='/idea/edit' />">Post new Idea</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid">