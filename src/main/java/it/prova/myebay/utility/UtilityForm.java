package it.prova.myebay.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;

public class UtilityForm {

	public static Annuncio createAnnuncioFromParams(String testoInputParam, Integer prezzoInputParam,
			Utente utenteParam, Set<Categoria> categorieParam) {

		Annuncio result = new Annuncio(testoInputParam, prezzoInputParam, utenteParam, categorieParam);
		result.setAperto(true);
		result.setDataPubblicazione(new Date());
		return result;
	}

	public static boolean validateAnnuncioBean(Annuncio annuncioToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(annuncioToBeValidated.getTestoAnnuncio())
				|| annuncioToBeValidated.getCategorie().isEmpty()
				|| annuncioToBeValidated.getUtenteInserimento()==null
				|| annuncioToBeValidated.getPrezzo() == null
				|| annuncioToBeValidated.getDataPubblicazione() == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateAcquistoBean(Acquisto acquistoToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(acquistoToBeValidated.getDescrizione())
				|| acquistoToBeValidated.getUtenteAcquirente()==null
				|| acquistoToBeValidated.getPrezzo() == null
				|| acquistoToBeValidated.getDataAcquisto() == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateUtenteBean(Utente utenteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(utenteToBeValidated.getNome())
				|| StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getPassword()) 
				|| StringUtils.isBlank(utenteToBeValidated.getUsername())){
			return false;
		}
		return true;
	}
	
	public static boolean validateConfermaPassword(String password, String confermaPassword) {
		
		if(!password.equals(confermaPassword))
			return false;
		
		return true;
	}
	
//	public static Film createFilmFromParams(String titoloInputParam, String genereInputParam,
//			String minutiDurataInputParam, String dataPubblicazioneStringParam, String registaIdStringParam) {
//
//		Film result = new Film(titoloInputParam, genereInputParam);
//		if (NumberUtils.isCreatable(minutiDurataInputParam)) {
//			result.setMinutiDurata(Integer.parseInt(minutiDurataInputParam));
//		}
//		result.setDataPubblicazione(parseDateArrivoFromString(dataPubblicazioneStringParam));
//		if (NumberUtils.isCreatable(registaIdStringParam)) {
//			result.setRegista(new Regista(Long.parseLong(registaIdStringParam)));
//		}
//		return result;
//	}

//	public static boolean validateFilmBean(Film filmToBeValidated) {
//		// prima controlliamo che non siano vuoti i parametri
//		if (StringUtils.isBlank(filmToBeValidated.getTitolo())
//				|| StringUtils.isBlank(filmToBeValidated.getGenere())
//				|| filmToBeValidated.getMinutiDurata() == null 
//				|| filmToBeValidated.getMinutiDurata() < 1
//				|| filmToBeValidated.getRegista() == null
//				|| filmToBeValidated.getRegista().getId() == null 
//				|| filmToBeValidated.getRegista().getId() < 1) {
//			return false;
//		}
//		return true;
//	}

	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
		
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
