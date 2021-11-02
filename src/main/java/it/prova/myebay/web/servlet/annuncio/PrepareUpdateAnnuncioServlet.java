package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareUpdateAnnuncioServlet
 */
@WebServlet("/user/PrepareUpdateAnnuncioServlet")
public class PrepareUpdateAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idParam = Long.parseLong(request.getParameter("idAnnuncio"));
		
		try {
			Annuncio daModificare= MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElemento(idParam);
			request.setAttribute("update_annuncio_attr", daModificare);
			request.setAttribute("categorie_list_attribute",
					MyServiceFactory.getCategoriaServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/annuncio/update.jsp").forward(request, response);
	}

}
