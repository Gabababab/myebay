package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListAnnunciUtenteServlet
 */
@WebServlet("/ExecuteListAnnunciUtenteServlet")
public class ExecuteListAnnunciUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteListAnnunciUtenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("idUser");

		try {
			request.setAttribute("list_annunci_utente_attr", MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(idParam)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un.");
			request.getRequestDispatcher("/homepage.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/annuncio/listAnnunciUtente.jsp").forward(request, response);
	}

}
