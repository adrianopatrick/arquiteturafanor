package br.edu.fanor.progweb.arquitetura.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fanor.progweb.arquitetura.aspectj.Loggable;
import br.edu.fanor.progweb.arquitetura.aspectj.PermitAll;
import br.edu.fanor.progweb.arquitetura.aspectj.RolesAllowed;
import br.edu.fanor.progweb.arquitetura.dao.UsuarioDAO;
import br.edu.fanor.progweb.arquitetura.entity.examples.Usuarios;
import br.edu.fanor.progweb.arquitetura.exceptions.DAOException;

/**
 * @author patrick.cunha
 * 
 */
@Loggable
@Service
public class UsuarioBO {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@RolesAllowed(value = { "INCLUIR_USUARIO" })
	public void salvar(Usuarios usuario) {
		usuario.setAtivo(false);
		usuario.setPrimeiroAcesso(true);
		usuarioDAO.salvar(usuario);
	}

	@RolesAllowed(value = { "ALTERAR_USUARIO" })
	public void atualizar(Usuarios usuario) {
		usuarioDAO.atualizar(usuario);
	}

	@PermitAll
	@Loggable(enable = false)
	public Usuarios loggar(String email, String senha) {
		return usuarioDAO.buscarPorEmailSenha(email, senha);
	}

	@PermitAll
	@Loggable(enable = false)
	public Usuarios buscarUsuarioPorEmail(String email) {
		return usuarioDAO.buscarPorEmail(email);
	}

	@RolesAllowed(value = { "LISTAR_USUARIO" })
	@Loggable(enable = false)
	public List<Usuarios> listaUsuarioPorNome(String nome) {
		List<Usuarios> usuarios = usuarioDAO.listarPorNome(nome);
		return usuarios;
	}

	@PermitAll
	@Loggable(enable = false)
	public Usuarios buscarPorId(Integer id) {
		try {
			return usuarioDAO.buscaPorId(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RolesAllowed(value = { "EXCLUIR_USUARIO" })
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void excluir(Usuarios usuario) {
		try {
			usuario = usuarioDAO.buscaPorId(usuario.getId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		usuarioDAO.excluir(usuario);
	}

}
