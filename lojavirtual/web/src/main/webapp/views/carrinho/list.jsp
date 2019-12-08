<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css" />">	
<title>Comprar</title>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../header.jsp" %>
		<div class="row">
			<div class="col-3">
				<%@include file="../navbar.jsp" %>
			</div>
			<div class="col-8">
				<c:if test="${error != null}">
					<div class="row">
						<div class="col text-center p-2 mb-2 alert-danger">
							${error}
						</div>
					</div>
				</c:if>			
				<div class="row">
					<div class="col font-weight-bold text-center p-2 bg-info text-white">
						Comprar
					</div>
				</div>
				<form action="${pageContext.servletContext.contextPath}/carrinho" method="post" id="carrinho">
					<div class="row">
						<div class="col">
							<div class="input-group mb-3 mt-3">
								<div class="input-group-prepend">
							    	<span class="input-group-text">Cliente</span>
								</div>
								<select class="custom-select" name="cliente" >
									<c:forEach var="cliente" items="${listaClientes}">
										<option value="${cliente.id}">${cliente.username}</option>
									</c:forEach>
								</select>
				
							</div>								
						</div>
					</div>
					<div class="row">
						<div class="col-2 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
							Produto
						</div>
						<div class="col-2 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
							Preço
						</div>
						<div class="col-4 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
							Descrição
						</div>	
						<div class="col-2 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
							Quantidade
						</div>	
						<div class="col-2 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
							Comprar
						</div>	
					</div>	
					<c:forEach var="produto" items="${listaProdutos}">
						<div class="row">
							<div class="col-2 text-center p-1 mb-1">
								${produto.nome}
							</div>
							<div class="col-2 text-center p-1 mb-1">
								${produto.preco}
							</div>													
							<div class="col-4 text-center p-1 mb-1">
								${produto.descricao}
							</div>											
							<div class="col-2 text-center p-1 mb-1 d-flex justify-content-center">
								<input class="form-control col-6 text-center" type="number" min="1" name="quantidade-${produto.id}" value="1">
							</div>		
							<div class="col-2 text-center p-1 mb-1">
								<input class="form-control" type="checkbox" name="check" value="${produto.id}">
							</div>						
						</div>
					</c:forEach>		
					
					<div class="row">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="formaPagamento" id="boleto" value="boleto" checked="checked">
						 	<label class="form-check-label" for="boleto">Boleto</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="formaPagamento" id="cartao" value="cartao">
							<label class="form-check-label" for="cartao">Cartão</label>
						</div>			
					</div>
					<div class="row">								
						<div class="col-sm-2 text-center">
							<input type="image" src="resources/img/shopping_cart-24px.svg" alt="Submit" id="sub-button">
						</div>
					</div>			
				</form>
				<div class="row">								
					<div class="col-sm-2 font-weight-bold text-center">
						Checkout
					</div>
				</div>							
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.4.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.bundle.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/script.js" />"></script>
</body>
</html>