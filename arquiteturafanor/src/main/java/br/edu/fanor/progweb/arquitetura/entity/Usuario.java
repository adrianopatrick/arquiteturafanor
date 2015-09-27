package br.edu.fanor.progweb.arquitetura.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String matricula;
	
	private String login;
	
	private String nome;
	
	private String senha;
	
	@OneToOne(mappedBy = "Coordenador")
	/*
	 * @JoinTable(name = "funcionario",
			joinColumns = @JoinColumn(name="usuario_id",referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="funcionario_id" ,referencedColumnName="id"))
	*/
	private Coordenador coordenador;
	
	@OneToOne(mappedBy = "Aluno")
	private Aluno aluno;

	public Usuario() {
	}

	public Usuario(String matricula, String login, String nome, String senha) {
		this.matricula = matricula;
		this.login = login;
		this.nome = nome;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setFuncionarios(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setFuncionarios(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
}
