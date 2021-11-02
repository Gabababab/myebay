<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../header.jsp" />

<title>Cronologia Acquisti</title>
</head>

<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div
				class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }"
				role="alert">
				${successMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<div class="alert alert-danger alert-dismissible fade show d-none"
				role="alert">
				Esempio di operazione fallita!
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<div class="alert alert-info alert-dismissible fade show d-none"
				role="alert">
				Aggiungere d-none nelle class per non far apparire
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>



			<div class='card'>
				<div class='card-header'>
					<h5>Riepilogo acquisti</h5>
				</div>
				<div class='card-body'>

					<div class='table-responsive'>
						<table class='table table-striped '>
							<thead>
								<tr>
									<th>Acquisto</th>
									<th>Prezzo</th>
									<th>Data di Acquisto</th>
									<th>Proprietario dell'acquisto</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${acquisto_list_attribute }" var="acquistiItem">
									<tr>
										<td>${acquistiItem.descrizione }</td>
										<td>${acquistiItem.prezzo }</td>
										<td><fmt:formatDate type="date"
												value="${acquistiItem.dataAcquisto }" /></td>
										<td>${acquistiItem.utenteAcquirente }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div class='card-footer'>
							<a href="/myebay/home" class='btn btn-navbar'>
							<i class='fa fa-chevron-left'></i> Indietro
						</a>
					</div>
					<!-- end card-body -->
				</div>
				<!-- end card -->
			</div>


			<!-- end container -->
		</div>

	</main>

	<!-- Footer -->
	<jsp:include page="../footer.jsp" />

</body>
</html>