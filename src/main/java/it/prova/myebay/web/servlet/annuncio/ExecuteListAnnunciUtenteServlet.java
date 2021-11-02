package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListAnnunciUtenteServlet
 */
@WebServlet("/ExecuteListAnnunciUtenteServlet")
public class ExecuteListAnnunciUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");
		Annuncio example = new Annuncio(utenteInSessione);
		request.setAttribute("annuncio_list_attribute",
				MyServiceFactory.getAnnuncioServiceInstance().findByExampleEager(example));
	} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
		request.getRequestDispatcher("home").forward(request, response);
		return;
	}

	// andiamo ai risultati
	request.getRequestDispatcher("/annuncio/listAnnunciUtente.jsp").forward(request, response);
	}


}
