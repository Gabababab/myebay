package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;
import java.util.Date;

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
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String idAnnuncioParam = request.getParameter("idAnnuncio");
		String prezzoParam = request.getParameter("prezzo");
		Utente utenteCompratore = (Utente) httpRequest.getSession().getAttribute("userInfo");

		try {
			Annuncio annuncioCompra = MyServiceFactory.getAnnuncioServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idAnnuncioParam));

			if (utenteCompratore.getCreditoResiduo() - Integer.parseInt(prezzoParam) < 0) {
				request.setAttribute("dettaglio_annunci_attr", annuncioCompra);
				request.setAttribute("errorMessage", "Credito Insufficiente.");
				request.getRequestDispatcher("/annuncio/show.jsp").forward(request, response);
				return;
			}
			Acquisto acquistoEffettuato = new Acquisto(annuncioCompra.getTestoAnnuncio(), annuncioCompra.getPrezzo(),
					new Date());
			acquistoEffettuato.setUtenteAcquirente(utenteCompratore);

			utenteCompratore.setCreditoResiduo(utenteCompratore.getCreditoResiduo() - Integer.parseInt(prezzoParam));
			annuncioCompra.setAperto(false);

			MyServiceFactory.getAcquistoServiceInstance().inserisciNuovo(acquistoEffettuato);
			MyServiceFactory.getUtenteServiceInstance().aggiorna(utenteCompratore);
			MyServiceFactory.getAnnuncioServiceInstance().aggiorna(annuncioCompra);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("/annuncio/show.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/acquisto/listAcquistiUtente.jsp").forward(request, response);
	}

}
