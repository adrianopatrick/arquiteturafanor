package br.edu.fanor.progweb.arquitetura.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@OneToMany(mappedBy = "alocado")
	private Alocado alocado;
	
	@OneToMany(mappedBy = "Aluno")
	private List<Aluno> alunos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Alocado getAlocado() {
		return alocado;
	}

	public void setAlocado(Alocado alocado) {
		this.alocado = alocado;
	}

	
	
}
