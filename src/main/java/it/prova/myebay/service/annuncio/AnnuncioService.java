package it.prova.myebay.service.annuncio;

import java.util.List;

import it.prova.myebay.dao.annuncio.AnnuncioDAO;
import it.prova.myebay.dao.categoria.CategoriaDAO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;

public interface AnnuncioService {
	
	public List<Annuncio> listAll() throws Exception;

	public Annuncio caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Annuncio annuncioInstance) throws Exception;

	public void inserisciNuovo(Annuncio annuncioInstance) throws Exception;

	public void rimuovi(Annuncio annuncioInstance) throws Exception;

	public Annuncio cercaPerTestoECategoria(String descrizione, List<Categoria> categorie) throws Exception;
	
	public Annuncio findByDescrizioneAndPrezzo(String descrizione, Integer prezzo) throws Exception;
	
	public void aggiungiCategorieDaVettore(Annuncio annuncioIntance, String[] categorieInstance) throws Exception;
	
	public List<Annuncio> findByExample(Annuncio example) throws Exception;
	
	List<Annuncio> findByExampleEager(Annuncio example) throws Exception;

	Annuncio caricaSingoloElementoEager(Long id) throws Exception;

	// per injection
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);
	
	public void setCategoriaDAO(CategoriaDAO categoriaDAO);


}
