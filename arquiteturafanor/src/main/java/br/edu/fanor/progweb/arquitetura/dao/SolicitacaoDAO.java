package br.edu.fanor.progweb.arquitetura.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fanor.progweb.arquitetura.entity.Solicitacao;
import br.edu.fanor.progweb.arquitetura.exceptions.DAOException;

/**
 * @author Samantha Silva
 **/

@Repository
@Transactional(propagation = Propagation.REQUIRED)

public class SolicitacaoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Solicitacao solicitacao) {
		entityManager.persist(solicitacao);
	}

	public void atualizar(Solicitacao solicitacao) {
		entityManager.merge(solicitacao);
	}

	public Solicitacao buscarPorSolicitacao(Calendar data ) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Solicitacao> criteriaQuery = criteriaBuilder.createQuery(Solicitacao.class);
		Root<Solicitacao> solicitacao = criteriaQuery.from(Solicitacao.class);
		criteriaQuery.where(criteriaBuilder.equal(solicitacao.<String> get("data"), data));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Solicitacao) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Solicitacao buscarPorSolicitacaoHorario(Calendar data, Calendar hora) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Solicitacao> criteriaQuery = criteriaBuilder.createQuery(Solicitacao.class);
		Root<Solicitacao> solicitacao = criteriaQuery.from(Solicitacao.class);
		criteriaQuery.where(criteriaBuilder.equal(solicitacao.<String> get("data"), data));
		criteriaQuery.where(criteriaBuilder.equal(solicitacao.<String> get("hora"), hora));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Solicitacao) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Solicitacao> listarPorHorario(Calendar hora ) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Solicitacao> criteriaQuery = criteriaBuilder.createQuery(Solicitacao.class);
		Root<Solicitacao> solicitacao = criteriaQuery.from(Solicitacao.class);
		criteriaQuery.where(criteriaBuilder.like(solicitacao.<String> get("hora"), "%" + hora + "%"));

		Query query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public Solicitacao buscaPorId(Integer id) throws DAOException {
		String jpql = "select s from Solicitacao s where s.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);

		try {
			return (Solicitacao) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public void excluir(Solicitacao solicitacao) {
		entityManager.remove(solicitacao);
	}
}
