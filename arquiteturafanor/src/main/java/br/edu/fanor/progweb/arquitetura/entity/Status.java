package br.edu.fanor.progweb.arquitetura.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricoes;
	
	@OneToMany(mappedBy = "Solicitacoes")
	private Solicitacao solitacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricoes() {
		return descricoes;
	}

	public void setDescricoes(String descricoes) {
		this.descricoes = descricoes;
	}

	public Solicitacao getSolitacoes() {
		return solitacoes;
	}

	public void setSolitacoes(Solicitacao solitacoes) {
		this.solitacoes = solitacoes;
	}
	
	
}
