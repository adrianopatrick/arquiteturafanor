package br.edu.fanor.progweb.arquitetura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fanor.progweb.arquitetura.entity.Status;

/**
 * @author Samantha Silva
 **/

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class StatusDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Status status) {
		entityManager.persist(status);
	}

	public void atualizar(Status status) {
		entityManager.merge(status);
	}

	@SuppressWarnings("unchecked")
	public List<Status> listarPorStatus(String descricoes) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Status> criteriaQuery = criteriaBuilder.createQuery(Status.class);
		Root<Status> status= criteriaQuery.from(Status.class);
		criteriaQuery.where(criteriaBuilder.like(status.<String> get("descricoes"), "%" + descricoes + "%"));

		Query query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public void excluir(Status status) {
		entityManager.remove(status);
	}
}
