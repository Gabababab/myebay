package it.prova.myebay.dao.annuncio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;

public class AnnuncioDAOImpl implements AnnuncioDAO{

	private EntityManager entityManager;

	@Override
	public List<Annuncio> list() throws Exception {
		return entityManager.createQuery("from Annuncio", Annuncio.class).getResultList();
	}

	@Override
	public Optional<Annuncio> findOne(Long id) throws Exception {
		Annuncio result = entityManager.find(Annuncio.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Annuncio input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.merge(input);
	}

	@Override
	public void insert(Annuncio input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Annuncio input) throws Exception {
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
	public List<Annuncio> findByExample(Annuncio example) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select a from Annuncio a join a.categorie c where a.id = a.id ");

		if (StringUtils.isNotEmpty(example.getTestoAnnuncio())) {
			whereClauses.add(" a.testoannuncio like :testo ");
			paramaterMap.put("testo", "%" + example.getTestoAnnuncio() + "%");
		}
		
		if (example.getPrezzo() != null) {
			whereClauses.add("a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo ", example.getPrezzo());
		}
		
		if (example.getCategorie().isEmpty()) {
			for(Categoria item:example.getCategorie()) {
				whereClauses.add(" c.id = :id");
				paramaterMap.put("id", item.getId());
			}

		}
		
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public Annuncio findByDescrizioneAndPrezzo(String testoannuncio, Integer prezzo) throws Exception {
		TypedQuery<Annuncio> query = entityManager
				.createQuery("select a from Annuncio a where a.testoannuncio=?1 and a.prezzo=?2", Annuncio.class)
				.setParameter(1, testoannuncio)
				.setParameter(2,  prezzo);
		
		return query.getResultStream().findFirst().orElse(null);
	}

}
