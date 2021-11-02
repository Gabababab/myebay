
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../header.jsp" />

<title>Dettagli annuncio</title>

</head>
<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div
				class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }"
				role="alert">
				${errorMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			
			<div class='card'>
				<div class='card-header'>
					<h5>Visualizza dettaglio</h5>
				</div>


				<div class='card-body'>
					<dl class="row">
						<dt class="col-sm-3 text-right">Id:</dt>
						<dd class="col-sm-9">${dettaglio_annuncio_attr.id}</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Testo Annuncio:</dt>
						<dd class="col-sm-9">${dettaglio_annuncio_attr.testoAnnuncio}</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Prezzo:</dt>
						<dd class="col-sm-9">${dettaglio_annuncio_attr.prezzo}</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Data Inserimento:</dt>
						<dd class="col-sm-9">
							<fmt:formatDate type="date"
								value="${dettaglio_annuncio_attr.dataPubblicazione}" />
						</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Categorie:</dt>
						<c:forEach items="${dettaglio_annuncio_attr.categorie}"
							var="categoriaItem">
							<dd class="col-sm-9">${categoriaItem.descrizione}</dd>
						</c:forEach>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Disponibilità:</dt>
						<dd class="col-sm-9">${dettaglio_annuncio_attr.aperto}</dd>
					</dl>


				</div>
				<!-- end card body -->

				<div class='card-footer'>
					<c:choose>
						<c:when test="${userInfo.isUser() || userInfo.isAdmin() }">
							<c:set
								value="${pageContext.request.contextPath}/user/ExecuteEffettuaAcquistoServlet"
								var="address"></c:set>
						</c:when>
						<c:otherwise>
							<c:set
								value="${pageContext.request.contextPath}/PrepareLoginServlet"
								var="address"></c:set>
						</c:otherwise>
					</c:choose>

					<form method="post" action="${address}" class="row g-3"
						novalidate="novalidate">

						<input type="hidden" name="idAnnuncio"
							value="${dettaglio_annuncio_attr.id}"> <input
							type="hidden" name="prezzo"
							value="${dettaglio_annuncio_attr.prezzo}">


						<div class="col-12">

							<button type="submit" name="submit" value="submit" id="submit"
								class="btn btn-outline-success">Compra</button>

							<a
								href="${pageContext.request.contextPath}/user/ExecuteListAnnuncioServlet"
								class='btn btn-outline-secondary' style='width: 150px'> <i
								class='fa fa-chevron-left'></i> Torna ad annunci
							</a>
						</div>
					</form>
				</div>

			</div>
			<!-- end card -->

		</div>


		<!-- end container -->

	</main>

	<!-- Footer -->
	<jsp:include page="../footer.jsp" />
</body>
</html>