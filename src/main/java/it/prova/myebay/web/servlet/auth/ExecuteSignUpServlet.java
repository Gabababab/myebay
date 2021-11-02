package it.prova.myebay.web.servlet.auth;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteSignUpServlet
 */
@WebServlet("/ExecuteSignUpServlet")
public class ExecuteSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String confermaPasswordParam = request.getParameter("confermapassword");
		
		
		Utente example = new Utente( usernameParam, passwordParam, nomeParam, cognomeParam);

		try {
			if(!UtilityForm.validateConfermaPassword(passwordParam, confermaPasswordParam)) {
				request.setAttribute("update_utente_attr", example);
				request.setAttribute("errorMessage", "Attenzione, Conferma password diversa da password");
				request.getRequestDispatcher("/utente/signup.jsp").forward(request, response);
				return;
			}
			
			example.setDateCreated(new Date());
			example.setStato(StatoUtente.CREATO);
			example.setCreditoResiduo(0);
			
			
			if(!UtilityForm.validateUtenteBean(example)) {
				request.setAttribute("signup_utente_attr", example);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/utente/signup.jsp").forward(request, response);
				return;
			}		
			
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(example);
			MyServiceFactory.getUtenteServiceInstance().aggiungiRuolo(example,
			MyServiceFactory.getRuoloServiceInstance().cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		response.sendRedirect("");
	}

}
