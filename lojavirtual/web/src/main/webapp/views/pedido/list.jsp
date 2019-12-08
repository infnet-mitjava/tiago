<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="br.edu.infnet.modelo.Carrinho"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<Carrinho> reverseListaCarrinhos = (List<Carrinho>)request.getAttribute("listaCarrinhos");
Collections.reverse(reverseListaCarrinhos);
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css" />">	
<title>Pedidos</title>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../header.jsp" %>
		<div class="row">
			<div class="col-3">
				<%@include file="../navbar.jsp" %>
			</div>
			<div class="col-9">				
				<div class="row">
					<div class="col font-weight-bold text-center p-2 mb-2 bg-info text-white">
						Pedidos
					</div>
				</div>							
				<div class="row">
					<div class="col-2 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
						Usuário
					</div>
					<div class="col-2 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
						Produto
					</div>
					<div class="col-4 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
						Descrição
					</div>						
					<div class="col-2 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
						Preço
					</div>
					<div class="col-2 font-weight-bold text-center p-3 mb-2 bg-dark text-white">
						Pagamento
					</div>
				</div>	
				<c:forEach var="carrinho" items="<%=reverseListaCarrinhos%>">
					<c:if test="${carrinho.getPago() == true}">
						<div class="row alert-success">
					</c:if>
					<c:if test="${carrinho.getPago() != true}">
						<div class="row alert-warning">
					</c:if>
						<div class="col-2 text-center p-1 mb-1 align-self-center">
							${carrinho.getCliente().getUsername()}
						</div>
						<div class="col-2 text-center p-1 mb-1 align-self-center">
							<c:forEach var="produto" items="${carrinho.getProdutos()}" >
								<p class="pt-0 pb-0 mb-0 mt-0">${produto.getNome()}</p>
							</c:forEach>
						</div>
						<div class="col-4 text-center p-1 mb-1 align-self-center">
							<c:forEach var="produto" items="${carrinho.getProdutos()}" >
								<p class="pt-0 pb-0 mb-0 mt-0">${produto.getDescricao()}</p>
							</c:forEach>	
						</div>	
						<div class="col-2 text-center p-1 mb-1 align-self-center">
							<c:forEach var="produto" items="${carrinho.getProdutos()}" >
								<p class="pt-0 pb-0 mb-0 mt-0">${produto.getPreco()}</p>
							</c:forEach>
						</div>					
						<c:if test="${carrinho.getPago() == true}">
							<div class="col-2 text-center p-1 mb-1 align-self-center">
								Pago - ${carrinho.getFormaPagamento()}
							</div>	
						</c:if>					
						<c:if test="${carrinho.getPago() != true}">
							<div class="col-2 text-center p-1 mb-1 align-self-center">
								Pendente - ${carrinho.getFormaPagamento()}
							</div>	
						</c:if>					
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