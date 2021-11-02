package it.prova.myebay.web.servlet.utente;

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
 * Servlet implementation class ExecuteUpdateUtenteServlet
 */
@WebServlet("/ExecuteUpdateUtenteServlet")
public class ExecuteUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParam = request.getParameter("idUtente");
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String confermaPasswordParam = request.getParameter("conpassword");
		String creditoParam = request.getParameter("creditoResiduo");
		String statoParam = request.getParameter("stato");
		
		
		Utente daModificare = new Utente(Long.parseLong(idParam), usernameParam, passwordParam, nomeParam, cognomeParam, Integer.parseInt(creditoParam));

		try {
			
			if (!UtilityForm.validateConfermaPassword(passwordParam, confermaPasswordParam)) {
				
				request.setAttribute("list_ruoli_attr", MyServiceFactory.getRuoloServiceInstance().listAll());
				request.setAttribute("insert_utente_attr", daModificare);
				request.setAttribute("errorMessage", "Attenzione password non valida");
				request.getRequestDispatcher("/utente/update.jsp").forward(request, response);
				return;
			}
			
			daModificare.setDateCreated(new Date());
			daModificare.setStato(StatoUtente.valueOf(statoParam));
			
			if(!UtilityForm.validateUtenteBean(daModificare)) {
				request.setAttribute("update_utente_attr", daModificare);
				request.setAttribute("ruoli_list_attribute",
						MyServiceFactory.getRuoloServiceInstance().listAll());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/utente/update.jsp").forward(request, response);
				return;
			}		
			
			MyServiceFactory.getUtenteServiceInstance().aggiorna(daModificare);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");
	}

}
