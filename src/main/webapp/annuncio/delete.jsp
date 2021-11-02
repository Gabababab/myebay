
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../header.jsp" />

<title>Rimuovi annuncio</title>

</head>
<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div class='card'>
				<div class='card-header'>
					<h5>Conferma rimozione</h5>
				</div>
				<form
					action="${pageContext.request.contextPath}/user/ExecuteDeleteAnnuncioServlet"
					method="post">
					<div class='card-body'>

						<dl class="row">
							<dt class="col-sm-3 text-right">Id:</dt>
							<dd class="col-sm-9">${delete_annuncio_attr.id}</dd>
						</dl>

						<dl class="row">
							<dt class="col-sm-3 text-right">Testo Annuncio:</dt>
							<dd class="col-sm-9">${delete_annuncio_attr.testoAnnuncio}</dd>
						</dl>

						<dl class="row">
							<dt class="col-sm-3 text-right">Prezzo:</dt>
							<dd class="col-sm-9">${delete_annuncio_attr.prezzo}</dd>
						</dl>

						<dl class="row">
							<dt class="col-sm-3 text-right">Data Inserimento:</dt>
							<dd class="col-sm-9">
								<fmt:formatDate type="date"
									value="${delete_annuncio_attr.dataPubblicazione}" />
							</dd>
						</dl>

						<dl class="row">
							<dt class="col-sm-3 text-right">Categorie:</dt>
							<c:forEach items="${delete_annuncio_attr.categorie}"
								var="categoriaItem">
								<dd class="col-sm-9">${categoriaItem.descrizione}</dd>
							</c:forEach>
						</dl>

						<dl class="row">
							<dt class="col-sm-3 text-right">Disponibilità:</dt>
							<dd class="col-sm-9">${delete_annuncio_attr.aperto}</dd>
						</dl>


					</div>
					<!-- end card body -->

					<div class='card-footer'>

						<input type="hidden" name="idAnnuncio"
							value="${delete_annuncio_attr.id}">
						<button type="submit" name="submit" id="submit"
							class="btn btn-danger">Conferma</button>
						<a
							href="${pageContext.request.contextPath}/user/ExecuteListAnnuncioServlet"
							class='btn btn-outline-secondary' style='width: 150px'> <i
							class='fa fa-chevron-left'></i> Torna ad annunci
						</a>

					</div>

				</form>
				<!-- end card -->
			</div>


			<!-- end container -->
		</div>

	</main>

	<!-- Footer -->
	<jsp:include page="../footer.jsp" />
</body>
</html>