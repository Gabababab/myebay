package it.prova.myebay.dao.acquisto;

import java.util.List;

import it.prova.myebay.dao.IBaseDAO;
import it.prova.myebay.model.Acquisto;

public interface AcquistoDAO extends IBaseDAO<Acquisto>{

	List<Acquisto> findByExample(Acquisto example) throws Exception;

	List<Acquisto> findByExampleEager(Acquisto example) throws Exception;

}
