package it.prova.myebay.service;

import it.prova.myebay.dao.ruolo.RuoloDAOImpl;
import it.prova.myebay.dao.utente.UtenteDAOImpl;
import it.prova.myebay.service.utente.UtenteService;
import it.prova.myebay.service.utente.UtenteServiceImpl;

public class MyServiceFactory {

	private static UtenteService UTENTE_SERVICE_INSTANCE;
	private static RuoloService RUOLO_SERVICE_INSTANCE;
	private static UtenteDAO UTENTE_DAO_INSTANCE = null;
	private static RuoloDAO RUOLO_DAO_INSTANCE = null;

	public static UtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTE_DAO_INSTANCE == null)
			UTENTE_DAO_INSTANCE = new UtenteDAOImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDAO(UTENTE_DAO_INSTANCE);
		
		if (RUOLO_SERVICE_INSTANCE == null)
			RUOLO_SERVICE_INSTANCE = new RuoloServiceImpl();

		if (RUOLO_DAO_INSTANCE == null)
			RUOLO_DAO_INSTANCE = new RuoloDAOImpl();

		UTENTE_SERVICE_INSTANCE.setRuoloDAO(RUOLO_DAO_INSTANCE);
		
		return UTENTE_SERVICE_INSTANCE;
	}

	public static RuoloService getRuoloServiceInstance() {
		if (RUOLO_SERVICE_INSTANCE == null)
			RUOLO_SERVICE_INSTANCE = new RuoloServiceImpl();

		if (RUOLO_DAO_INSTANCE == null)
			RUOLO_DAO_INSTANCE = new RuoloDAOImpl();

		RUOLO_SERVICE_INSTANCE.setRuoloDAO(RUOLO_DAO_INSTANCE);
		return RUOLO_SERVICE_INSTANCE;
	}


}
