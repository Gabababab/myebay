package it.prova.myebay.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "acquisto")
public class Acquisto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "prezzo")
	private Integer prezzo;
	@Column(name = "dataacquisto")
	private Date dataAcquisto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utenteAcquirente;

	
	public Acquisto() {
		super();
	}

	public Acquisto(String descrizione, Integer prezzo, Date dataAcquisto, Utente utenteAcquirente) {
		super();
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.dataAcquisto = dataAcquisto;
		this.utenteAcquirente = utenteAcquirente;
	}

	public Acquisto(String testoAnnuncio, Integer prezzo, Utente utenteInserimento, Date date) {
		this.descrizione=testoAnnuncio;
		this.prezzo=prezzo;
		this.utenteAcquirente=utenteInserimento;
		this.dataAcquisto=date;
	}

	public Acquisto(Utente utente) {
		this.utenteAcquirente=utente;
	}

	public Acquisto(String testoAnnuncio, Integer prezzo, Date date) {
		this.descrizione=testoAnnuncio;
		this.prezzo=prezzo;
		this.dataAcquisto=date;
	}

	public Acquisto(String testoAnnuncio, Date date, Integer prezzo, Utente utente) {
		this.descrizione=testoAnnuncio;
		this.prezzo=prezzo;
		this.dataAcquisto=date;
		this.utenteAcquirente=utente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Date getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public Utente getUtenteAcquirente() {
		return utenteAcquirente;
	}

	public void setUtenteAcquirente(Utente utenteAcquirente) {
		this.utenteAcquirente = utenteAcquirente;
	}
	
	
}
