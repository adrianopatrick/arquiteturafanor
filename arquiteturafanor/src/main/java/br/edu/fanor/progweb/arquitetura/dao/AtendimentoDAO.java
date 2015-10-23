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

import br.edu.fanor.progweb.arquitetura.entity.Atendimento;
import br.edu.fanor.progweb.arquitetura.exceptions.DAOException;

/**
 * @author Samantha Silva
 **/

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class AtendimentoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Atendimento atendimento) {
		entityManager.persist(atendimento);
	}

	public void atualizar(Atendimento atendimento) {
		entityManager.merge(atendimento);
	}

	public Atendimento buscarPorSolicitacao(Calendar data) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteriaQuery = criteriaBuilder.createQuery(Atendimento.class);
		Root<Atendimento> atendimento = criteriaQuery.from(Atendimento.class);
		criteriaQuery.where(criteriaBuilder.equal(atendimento.<String> get("data"), data));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Atendimento) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Atendimento buscarPorSolicitacaoHorario(Calendar data, Calendar hora) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteriaQuery = criteriaBuilder.createQuery(Atendimento.class);
		Root<Atendimento> atendimento = criteriaQuery.from(Atendimento.class);
		criteriaQuery.where(criteriaBuilder.equal(atendimento.<String> get("data"), data));
		criteriaQuery.where(criteriaBuilder.equal(atendimento.<String> get("hora"), hora));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Atendimento) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> listarPorHorario(Calendar hora) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteriaQuery = criteriaBuilder.createQuery(Atendimento.class);
		Root<Atendimento> atendimento = criteriaQuery.from(Atendimento.class);
		criteriaQuery.where(criteriaBuilder.like(atendimento.<String> get("hora"), "%" + hora + "%"));

		Query query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public Atendimento buscaPorId(Integer id) throws DAOException {
		String jpql = "select a from Atendimento a where s.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);

		try {
			return (Atendimento) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public void excluir(Atendimento atendimento) {
		entityManager.remove(atendimento);
	}

}
