package it.prova.myebay.web.listener;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.service.annuncio.AnnuncioService;
import it.prova.myebay.service.categoria.CategoriaService;
import it.prova.myebay.service.ruolo.RuoloService;
import it.prova.myebay.service.utente.UtenteService;

@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;

	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("myebay_unit");
			// questa chiamata viene fatta qui per semplicità ma in genere è meglio trovare
			// altri modi per fare init
			initAdminUserAndRuoliAndCategorieAndAnnunci();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return entityManagerFactory.createEntityManager();

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			try {
				if (em.isOpen()) {
					em.close();
				}
			} catch (PersistenceException ex) {
				System.err.println("Could not close JPA EntityManager" + ex);
			} catch (Throwable ex) {
				System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
			}
		}
	}

	private void initAdminUserAndRuoliAndCategorieAndAnnunci() throws Exception {
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();
		CategoriaService categoriaServiceInstance=MyServiceFactory.getCategoriaServiceInstance();
		AnnuncioService annuncioServiceInstance=MyServiceFactory.getAnnuncioServiceInstance(); 

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", "ROLE_CLASSIC_USER"));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Visitor", "ROLE_VISITOR") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Visitor", "ROLE_VISITOR"));
		}
		
		if(categoriaServiceInstance.cercaPerDescrizioneECodice("Elettronica", "CAT1") == null) {
			categoriaServiceInstance.inserisciNuovo(new Categoria("Elettronica", "CAT1"));
		}
		
		if(categoriaServiceInstance.cercaPerDescrizioneECodice("Abbigliamento", "CAT2") == null) {
			categoriaServiceInstance.inserisciNuovo(new Categoria("Abbigliamento", "CAT2"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("admin", "admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", 1000, new Date());
			admin.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.aggiungiRuolo(admin,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
		}
	}

}
