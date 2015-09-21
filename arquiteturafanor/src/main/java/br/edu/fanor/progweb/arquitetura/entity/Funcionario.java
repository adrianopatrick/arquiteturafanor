package br.edu.fanor.progweb.arquitetura.entity;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Funcionario {

	
	@ManyToMany(mappedBy = "Usuario")
	private List<Usuario> usuarios;
	
	@ManyToOne
	@JoinColumn(name = "tipo_funcionario_id")
	private List<TipoFuncionario> funcionarios;

	@OneToMany
	@JoinColumn(name = "usuario_id")
	private Alocado alocado;
	
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private TipoSolicitacao solicitacao;

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<TipoFuncionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<TipoFuncionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Alocado getAlocado() {
		return alocado;
	}

	public void setAlocado(Alocado alocado) {
		this.alocado = alocado;
	}

	public TipoSolicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(TipoSolicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	
	
}
