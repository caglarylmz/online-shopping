<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>OrionTech Shopping- ${title}</title>
<!-- Seçili sayfanmın titleını alır ve menu degiskenine atar -->
<script type="text/javascript">
	window.menu = '${title}';
	//context = http://localhost:8080/onlineshopping/
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.css" rel="stylesheet">
<!-- Theme CSS -->
<link href="${css}/bootstrap-darkly.css" rel="stylesheet">
<!-- jquery DataTable jquery css -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
<link href="${css}/jquery.dataTables.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${css}/homepage.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
		<nav class="navbar navbar-inverse navbar-fixeditop" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Home</a>
				</div>
			</div>
		</nav>
		<div class="content">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<div class="jumbotron">
							<h1>${errorTitle}</h1>
							<hr/>
							<blockquote>
								${errorDescription}
							</blockquote>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

	</div>
</body>
</html>