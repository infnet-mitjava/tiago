<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css" />">
<title>Início</title>
</head>
<body>
	<div class="container-fluid">
		<%@include file="header.jsp" %>
		<div class="row">
			<div class="col-3">
				<%@include file="navbar.jsp" %>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.4.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.bundle.min.js" />"></script>
</body>
</html>