package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteInsertAnnuncioServlet
 */
@WebServlet("/ExecuteInsertAnnuncioServlet")
public class ExecuteInsertAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertAnnuncioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String testoParam = request.getParameter("testo");
		String prezzoParam = request.getParameter("prezzo");
		String[] categorieParam = request.getParameterValues("categorieInput");
		String utenteIdParam = request.getParameter("idUtente");

		try {
			Annuncio annuncioInstance = UtilityForm.createAnnuncioFromParams(testoParam,
					Integer.parseInt(prezzoParam),
					MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(utenteIdParam)),
					categorieParam);

			if (!UtilityForm.validateAnnuncioBean(annuncioInstance)) {
				request.setAttribute("insert_annuncio_attr", annuncioInstance);
				request.setAttribute("categorie_list_attribute",
						MyServiceFactory.getCategoriaServiceInstance().listAll());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/annuncio/insert.jsp").forward(request, response);
				return;
			}

			MyServiceFactory.getAnnuncioServiceInstance().inserisciNuovo(annuncioInstance);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/annuncio/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("/user/ExecuteListAnnuncioServlet?operationResult=SUCCESS");

	}

}
