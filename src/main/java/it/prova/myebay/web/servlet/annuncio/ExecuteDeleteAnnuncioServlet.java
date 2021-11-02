package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exceptions.ElementNotFoundException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteAnnuncioServlet
 */
@WebServlet("/user/ExecuteDeleteAnnuncioServlet")
public class ExecuteDeleteAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String idAnnuncioParam = request.getParameter("idAnnuncio");

		
		

		try {

			if (!NumberUtils.isCreatable(idAnnuncioParam)) {
				// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore. (id)");
				request.getRequestDispatcher("/home").forward(request, response);
				return;
			}
			
			Annuncio daRimuovere = MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElemento(Long.parseLong(idAnnuncioParam));
			if (!daRimuovere.getAperto()) {
				request.setAttribute("errorMessage", "Attenzione l'annuncio è chiuso");
				request.getRequestDispatcher("/home").forward(request, response);
				return;
			}
			
			MyServiceFactory.getAnnuncioServiceInstance().rimuovi(daRimuovere);
		} catch (ElementNotFoundException e) {
			request.getRequestDispatcher("ExecuteListFilmServlet?operationResult=NOT_FOUND").forward(request, response);
			return;
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListAnnuncioServlet?operationResult=SUCCESS");
	}

}
