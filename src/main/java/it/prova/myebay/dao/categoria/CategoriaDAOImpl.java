package it.prova.myebay.dao.categoria;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;

public class CategoriaDAOImpl implements CategoriaDAO{

	private EntityManager entityManager;

	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Optional<Categoria> findOne(Long id) throws Exception {
		Categoria result = entityManager.find(Categoria.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Categoria input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.merge(input);
	}

	@Override
	public void insert(Categoria input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Categoria input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
	}

	@Override
	public Categoria findByDescrizioneAndCodice(String descrizione, String codice) throws Exception {
		TypedQuery<Categoria> query = entityManager
				.createQuery("select c from Categoria c where c.descrizione=?1 and c.codice=?2", Categoria.class)
				.setParameter(1, descrizione)
				.setParameter(2, codice);
		
		return query.getResultStream().findFirst().orElse(null);
	}

}
