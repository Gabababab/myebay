package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteSearchAnnuncioServlet
 */
@WebServlet("/ExecuteSearchAnnuncioServlet")
public class ExecuteSearchAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchAnnuncioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String testoParam=request.getParameter("testo");
		String prezzoParam=request.getParameter("prezzo");
		String[] categorieParam = request.getParameterValues("categorieInput");
		
		Annuncio annuncioExample=UtilityForm.createAnnuncioFromParams(testoParam, Integer.parseInt(prezzoParam), categorieParam);
		
			try {
								
				request.setAttribute("list_annuncio_attr", MyServiceFactory.getAnnuncioServiceInstance().findByExample(annuncioExample));
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Attenzione si è verificato un.");
				request.getRequestDispatcher("/homepage.jsp").forward(request, response);
				return;
			}
		
			request.getRequestDispatcher("/annuncio/list.jsp").forward(request, response);
	}

}
