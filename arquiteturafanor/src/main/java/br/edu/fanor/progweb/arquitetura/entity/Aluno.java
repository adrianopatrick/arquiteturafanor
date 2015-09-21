package br.edu.fanor.progweb.arquitetura.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Aluno {

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private List<Curso> cursos;

	public Usuario getUsuarios() {
		return usuario;
	}

	public void setUsuarios(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	
}
