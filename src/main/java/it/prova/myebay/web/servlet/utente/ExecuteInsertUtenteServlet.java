package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;


/**
 * Servlet implementation class ExecuteInsertUtenteServlet
 */
@WebServlet("/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String confermaPasswordParam = request.getParameter("passwordConferma");
		String[] ruoliParam = request.getParameterValues("ruoliInput");

		Utente utenteInstance = new Utente(usernameParam, passwordParam, nomeParam, cognomeParam, new Date());

		
		try {
			
			if (!UtilityForm.validateConfermaPassword(passwordParam, confermaPasswordParam)) {
				
				request.setAttribute("list_ruoli_attr", MyServiceFactory.getRuoloServiceInstance().listAll());
				request.setAttribute("insert_utente_attr", utenteInstance);
				request.setAttribute("errorMessage", "Attenzione password non valida");
				request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
				return;
			}
			
			if (!UtilityForm.validateUtenteBean(utenteInstance)) {
				
				request.setAttribute("list_ruoli_attr", MyServiceFactory.getRuoloServiceInstance().listAll());
				request.setAttribute("insert_utente_attr", utenteInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
				return;
			}
			
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovoConRuoli(utenteInstance, ruoliParam);
			request.setAttribute("list_ruoli_attr", MyServiceFactory.getRuoloServiceInstance().listAll());
			request.setAttribute("utente_list_attribute", MyServiceFactory.getUtenteServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("insert_utente_attr", utenteInstance);
			request.setAttribute("errorMessage", "Attenzione si è verificato un.");
			request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");
	}

}
