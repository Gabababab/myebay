package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteEffettuaAcquistoServlet
 */
@WebServlet("/user/ExecuteEffettuaAcquistoServlet")
public class ExecuteEffettuaAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("idAnnuncio"));
		
		try {
			
			Annuncio annuncio = MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElemento(id);
			Utente utente = (Utente)request.getSession().getAttribute("userInfo");
			utente = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(utente.getId());
			
			if(MyServiceFactory.getAnnuncioServiceInstance().compraAnnuncio(utente, annuncio) == true) {
				request.setAttribute("successMessage", "Acquisto effettuato.");
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				Utente utenteInSessione = (Utente) httpRequest.getSession().getAttribute("userInfo");
				Acquisto example = new Acquisto(utenteInSessione);

				request.setAttribute("acquisto_list_attribute",
						MyServiceFactory.getAcquistoServiceInstance().findByExampleEager(example));
				request.getRequestDispatcher("/acquisto/listAcquistiUtente.jsp").forward(request, response);
				return;
			}
			
			request.setAttribute("errorMessage", "Acquisto non effettuato.");
			request.getRequestDispatcher("").forward(request, response);
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
	}

}
