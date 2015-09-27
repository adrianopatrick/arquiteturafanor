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

import br.edu.fanor.progweb.arquitetura.entity.Coordenador;
import br.edu.fanor.progweb.arquitetura.exceptions.DAOException;

/**
 * @author Samantha Silva
 **/

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class CoordenadorDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Coordenador coordenador) {
		entityManager.persist(coordenador);
	}

	public void atualizar(Coordenador coordenador) {
		entityManager.merge(coordenador);
	}

	public Coordenador buscarPorEmail(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Coordenador> criteriaQuery = criteriaBuilder.createQuery(Coordenador.class);
		Root<Coordenador> coordenador = criteriaQuery.from(Coordenador.class);
		criteriaQuery.where(criteriaBuilder.equal(coordenador.<String> get("email"), email));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Coordenador) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Coordenador buscarPorEmailSenha(String email, String senha) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Coordenador> criteriaQuery = criteriaBuilder.createQuery(Coordenador.class);
		Root<Coordenador> coordenador = criteriaQuery.from(Coordenador.class);
		criteriaQuery.where(criteriaBuilder.equal(coordenador.<String> get("email"), email));
		criteriaQuery.where(criteriaBuilder.equal(coordenador.<String> get("senha"), senha));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Coordenador) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Coordenador> listarPorNome(String nome) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Coordenador> criteriaQuery = criteriaBuilder.createQuery(Coordenador.class);
		Root<Coordenador> coordenador = criteriaQuery.from(Coordenador.class);
		criteriaQuery.where(criteriaBuilder.like(coordenador.<String> get("nome"), "%" + nome + "%"));

		Query query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public Coordenador buscaPorId(Integer id) throws DAOException {
		String jpql = "select u from Usuarios u where u.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);

		try {
			return (Coordenador) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public void excluir(Coordenador coordenador) {
		entityManager.remove(coordenador);
	}
}
