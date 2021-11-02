package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteUpdateAnnuncioServlet
 */
@WebServlet("/user/ExecuteUpdateAnnuncioServlet")
public class ExecuteUpdateAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("idAnnuncio");
		String testoAnnuncioParam = request.getParameter("testoAnnuncio");
		String prezzoParam = request.getParameter("prezzo");
		String[] categorieParam = request.getParameterValues("categoria");
		
		Annuncio daModificare = UtilityForm.createAnnuncioFromParams(testoAnnuncioParam, Integer.parseInt(prezzoParam), categorieParam);

		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			
			if (!UtilityForm.validateAnnuncioBean(daModificare)) {
				request.setAttribute("update_annuncio_attr", daModificare);
				request.setAttribute("categorie_list_attribute",
						MyServiceFactory.getCategoriaServiceInstance().listAll());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/annuncio/insert.jsp").forward(request, response);
				return;
			}
			
			Annuncio annuncioInstance = MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElemento(Long.parseLong(idParam));
			if (!annuncioInstance.getAperto()) {
				request.setAttribute("errorMessage", "Attenzione l'annuncio è chiuso");
				request.getRequestDispatcher("/home").forward(request, response);
				return;
			}
			
			daModificare.setDataPubblicazione(new Date());
			daModificare.setAperto(true);
			daModificare.setUtenteInserimento((Utente)httpRequest.getSession().getAttribute("userInfo"));

			MyServiceFactory.getAnnuncioServiceInstance().aggiorna(daModificare);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/home").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListAnnuncioServlet?operationResult=SUCCESS");
	}

}
