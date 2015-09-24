package br.com.minhaloja.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.minhaloja.model.Usuario;

@Named(value="loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Usuario> usuarios;
	private Usuario usuario;
	
	@Inject
	private ProdutoBean produtoBean;
	
	@PostConstruct
	public void start(){
		
		this.setUsuario(new Usuario());
		this.usuarios = new ArrayList<Usuario>();
		
		this.usuarios.add(new Usuario("joao","123"));
		this.usuarios.add(new Usuario("maria","456"));
		this.usuarios.add(new Usuario("vitor","789"));
	}
	
	
	public void logar(Usuario login){
		String rota = "";
		for (Usuario usuario : usuarios) {
			if(
				(usuario.getNome().equals(login.getNome())) &&
				(usuario.getSenha().equals(login.getSenha()))
			){
				rota = "/minhaLoja/Produto/produtos.xhtml";
			}
		}
		produtoBean.rota(rota);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
