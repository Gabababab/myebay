package it.prova.myebay.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "annuncio")
public class Annuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "testoannuncio")
	private String testoAnnuncio;
	@Column(name = "prezzo")
	private Integer prezzo;
	@Column(name = "datapubblicazione")
	private Date dataPubblicazione;
	@Column(name = "aperto")
	private Boolean aperto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utenteInserimento;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "annuncio_categoria", joinColumns = @JoinColumn(name = "annuncio_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "ID"))
	private Set<Categoria> categorie= new HashSet<Categoria>();

	
	public Annuncio() {
		super();
	}
	
	

	public Annuncio(String testoAnnuncio, Integer prezzo, Date dataPubblicazione, Boolean aperto,
			Utente utenteInserimento, Set<Categoria> categorie) {
		super();
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.dataPubblicazione = dataPubblicazione;
		this.aperto = aperto;
		this.utenteInserimento = utenteInserimento;
		this.categorie = categorie;
	}



	public Annuncio(String testoAnnuncio, Integer prezzo, Date dataPubblicazione, Boolean aperto,
			Utente utenteInserimento) {
		super();
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.dataPubblicazione = dataPubblicazione;
		this.aperto = aperto;
		this.utenteInserimento = utenteInserimento;
	}



	public Annuncio(String testoAnnuncio, Integer prezzo) {
		super();
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
	}


	public Annuncio(String testoAnnuncio, Integer prezzo, Date dataPubblicazione) {
		super();
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.dataPubblicazione = dataPubblicazione;
	}

	public Annuncio(String testoAnnuncio, Integer prezzo, Utente utenteInserimento, Set<Categoria> categorie) {
		super();
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.utenteInserimento = utenteInserimento;
		this.categorie = categorie;
	}



	public Annuncio(Utente utente) {
		this.utenteInserimento=utente;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public Utente getUtenteInserimento() {
		return utenteInserimento;
	}

	public void setUtenteInserimento(Utente utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}

	public Boolean getAperto() {
		return aperto;
	}

	public void setAperto(Boolean aperto) {
		this.aperto = aperto;
	}
	
	
}
