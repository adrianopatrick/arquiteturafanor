package br.edu.fanor.progweb.arquitetura.dao;

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

import br.edu.fanor.progweb.arquitetura.entity.Aluno;
import br.edu.fanor.progweb.arquitetura.exceptions.DAOException;


/**
 * @author Samantha Silva
 **/


@Repository
@Transactional(propagation = Propagation.REQUIRED)

public class AlunoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Aluno aluno) {
		entityManager.persist(aluno);
	}

	public void atualizar(Aluno aluno) {
		entityManager.merge(aluno);
	}

	public Aluno buscarPorEmail(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Aluno> criteriaQuery = criteriaBuilder.createQuery(Aluno.class);
		Root<Aluno> aluno = criteriaQuery.from(Aluno.class);
		criteriaQuery.where(criteriaBuilder.equal(aluno.<String> get("email"), email));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Aluno) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Aluno buscarPorEmailSenha(String email, String senha) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Aluno> criteriaQuery = criteriaBuilder.createQuery(Aluno.class);
		Root<Aluno> aluno = criteriaQuery.from(Aluno.class);
		criteriaQuery.where(criteriaBuilder.equal(aluno.<String> get("email"), email));
		criteriaQuery.where(criteriaBuilder.equal(aluno.<String> get("senha"), senha));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Aluno) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> listarPorNome(String nome) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Aluno> criteriaQuery = criteriaBuilder.createQuery(Aluno.class);
		Root<Aluno> aluno = criteriaQuery.from(Aluno.class);
		criteriaQuery.where(criteriaBuilder.like(aluno.<String> get("nome"), "%" + nome + "%"));

		Query query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public Aluno buscaPorId(Integer id) throws DAOException {
		String jpql = "select u from Usuarios u where u.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);

		try {
			return (Aluno) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public void excluir(Aluno aluno) {
		entityManager.remove(aluno);
	}
}
