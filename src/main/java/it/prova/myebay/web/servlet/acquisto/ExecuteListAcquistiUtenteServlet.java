package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListAcquistiUtenteServlet
 */
@WebServlet("/user/ExecuteListAcquistiUtenteServlet")
public class ExecuteListAcquistiUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteListAcquistiUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpServletRequest httpRequest = (HttpServletRequest) request;

			Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");
			Acquisto example = new Acquisto(utenteInSessione);
			request.setAttribute("acquisto_list_attribute",
					MyServiceFactory.getAcquistoServiceInstance().findByExample(example));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/homepage.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/acquisto/listAcquistiUtente.jsp").forward(request, response);
	}


}
