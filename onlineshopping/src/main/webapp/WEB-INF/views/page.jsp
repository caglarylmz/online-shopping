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
<link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">
<link href="${css}/jquery.dataTables.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${css}/homepage.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>
		<!-- Page Content -->
		<div class="content">
			<!-- Load only when user clicks home -->
			<c:if test="${userClickHome == true}">
				<%@include file="./home.jsp"%>
			</c:if>
			<!-- Load only when user clicks about -->
			<c:if test="${userClickAbout == true}">
				<%@include file="./about.jsp"%>
			</c:if>
			<!-- Load only when user clicks contact -->
			<c:if test="${userClickContact == true}">
				<%@include file="./contact.jsp"%>
			</c:if>
			<!-- Load only when user clicks View Products -->
			<c:if test="${userClickAllProducts == true or userClickCategoryProducts==true}">
				<%@include file="./listProducts.jsp"%>
			</c:if>
			<!-- Load only when user clicks show product -->
			<c:if test="${userClickShowProduct == true}">
				<%@include file="./singleProduct.jsp"%>
			</c:if>
			<!-- Load only user clicks Manage Products -->
			<c:if test="${userClickManageProduct == true}">
				<%@include file="./manageProducts.jsp"%>
			</c:if>
		</div>
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>
		<!-- Scripts -->
		<!-- Bootstrap/JQuery core JavaScript -->
		<script src="${js}/jquery.js"></script>
		<script src="${js}/bootstrap.js"></script>
		<!--DataTable Plugin  -->
		<script src="${js}/jquery.dataTables.js"></script>	
		<script src="${js}/dataTables.bootstrap4.js"></script>	
		<!--load myapp.js  -->
		<script src="${js}/myapp.js"></script>

	</div>
</body>
</html>