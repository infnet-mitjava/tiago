<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css" />">	
<title>Clientes</title>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../header.jsp" %>
		<div class="row">
			<div class="col-3">
				<%@include file="../navbar.jsp" %>
			</div>
			<div class="col-6">
				<c:if test="${error != null}">
					<div class="row">
						<div class="col text-center p-2 mb-2 alert-danger">
							${error}
						</div>
					</div>
				</c:if>
				<c:if test="${cliente != null}">
					<div class="row">
						<div class="col text-center p-2 mb-2 alert-success">
							${cliente.username} adicionado com sucesso!
						</div>
					</div>
				</c:if>				
				<div class="row">
					<div class="col font-weight-bold text-center p-2 mb-2 bg-info text-white">
						Adicionar Cliente
					</div>
				</div>
				<div class="row">
					<div class="col">
						<form action="${pageContext.servletContext.contextPath}/cliente"  method="post">
				
							<div class="input-group mb-3">
								<div class="input-group-prepend">
							    	<span class="input-group-text">Username</span>
								</div>
								<input type="text" class="form-control" name="username" />
				
							</div>				
							<div class="input-group mb-3">
								<div class="input-group-prepend">
							    	<span class="input-group-text">E-mail</span>
								</div>
								<input type="text" class="form-control" name="email" />
				
							</div>				
			
							<p>
					    		<input id="submit" class="btn btn-outline-success" type="submit" value="Salvar" />
					    		<button id="back" class="btn btn-outline-info" onclick="goBack()">Go Back</button>
				    		</p>
				
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col font-weight-bold text-center p-2 bg-info text-white">
						Clientes
					</div>
				</div>
				<div class="row">
					<div class="col-6 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
						Username
					</div>
					<div class="col-6 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
						E-mail
					</div>	
				</div>	
				<c:forEach var="cliente" items="${listaClientes}">
					<div class="row">
						<div class="col-6 text-center p-1 mb-1">
							${cliente.getUsername()}
						</div>
						<div class="col-6 text-center p-1 mb-1">
							${cliente.getEmail()}
						</div>																						
					</div>
				</c:forEach>					
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.4.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.bundle.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/script.js" />"></script>
</body>
</html>