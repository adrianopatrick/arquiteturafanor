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

import br.edu.fanor.progweb.arquitetura.entity.Aluno;
import br.edu.fanor.progweb.arquitetura.entity.TipoSolicitacao;

/**
 * @author Samantha Silva
 **/

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TipoSolicitacaoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(TipoSolicitacao tipoSolicitacao) {
		entityManager.persist(tipoSolicitacao);
	}

	public void atualizar(TipoSolicitacao tipoSolicitacao) {
		entityManager.merge(tipoSolicitacao);
	}

	@SuppressWarnings("unchecked")
	public List<TipoSolicitacao> listarPorTipo(String descricao) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoSolicitacao > criteriaQuery = criteriaBuilder.createQuery(TipoSolicitacao .class);
		Root<TipoSolicitacao > tipoSolicitacao  = criteriaQuery.from(TipoSolicitacao .class);
		criteriaQuery.where(criteriaBuilder.like(tipoSolicitacao.<String> get("descricao"), "%" + descricao+ "%"));

		Query query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public void excluir(TipoSolicitacao tiposolicitacao) {
		entityManager.remove(tiposolicitacao);
	}
}
