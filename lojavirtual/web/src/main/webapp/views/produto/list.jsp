<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css" />">	
<title>Produtos</title>
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
				<c:if test="${produto != null}">
					<div class="row">
						<div class="col text-center p-2 mb-2 alert-success">
							${produto.nome} adicionado com sucesso!
						</div>
					</div>
				</c:if>					
				<div class="row">
					<div class="col font-weight-bold text-center p-2 mb-2 bg-info text-white">
						Adicionar Produto
					</div>
				</div>				
				<div class="row">
					<div class="col">
						<form action="${pageContext.servletContext.contextPath}/produto"  method="post">
				
							<div class="input-group mb-3">
								<div class="input-group-prepend">
							    	<span class="input-group-text">Nome</span>
								</div>
								<input type="text" class="form-control" name="nome" />
				
							</div>				
							<div class="input-group mb-3">
								<div class="input-group-prepend">
							    	<span class="input-group-text">Descrição</span>
								</div>
								<input type="text" class="form-control" name="descricao" />
				
							</div>				
							<div class="input-group mb-3">
								<div class="input-group-prepend">
							    	<span class="input-group-text">Preço</span>
								</div>
								<input type="text" class="form-control" name="preco" />
				
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
						Produtos
					</div>
				</div>
				<div class="row">
					<div class="col-4 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
						Produto
					</div>
					<div class="col-4 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
						Descrição
					</div>						
					<div class="col-4 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
						Preço
					</div>
				</div>	
				<c:forEach var="produto" items="${listaProdutos}">
					<div class="row">
						<div class="col-4 text-center p-1 mb-1">
							${produto.getNome()}
						</div>
						<div class="col-4 text-center p-1 mb-1">
							${produto.getDescricao()}
						</div>									
						<div class="col-4 text-center p-1 mb-1">
							${produto.getPreco()}
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