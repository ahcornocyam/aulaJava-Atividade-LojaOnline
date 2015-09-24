package br.com.minhaloja.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.minhaloja.model.Produto;

@Named(value="produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto;
	private List<Produto> produtos;
	@Inject
	private CarrinhoBean carrinhoBean;
	
	
	
	@PostConstruct
	public void start(){
		this.setProduto(new Produto());
		this.setProdutos(new ArrayList<Produto>());
		
		this.produtos.add(new Produto("Carro","Automovel",45000.00,30));
		this.produtos.add(new Produto("Moto","Automovel",3000.00,20));
		this.produtos.add(new Produto("HD","Informática",320.00,10));
		this.produtos.add(new Produto("Computador","Informática",5000.00,5));
		this.produtos.add(new Produto("Notebook","Informática",4000.00,40));
	}

	/*
	 * Getters and Setters
	 */	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	//salvar
	public void salvar(){
		if(!this.produtos.contains(produto)){
			this.produtos.add(this.produto);
		}
		this.produto = new Produto();
		rota("../produtos.xhtml");
	}
	
	/*
	 * função para redirecionar página
	 */
	public void rota(String page){
		try{
			FacesContext.
					getCurrentInstance().
					getExternalContext().
					redirect(page);
		}catch( IOException e){
			e.printStackTrace();
		}
	}
	
	//cadicionar ao carrinho
	public void adicionarCarrinho(Produto produto){
		if(this.produtos.contains(produto)){
			this.produto = produto;
			if(this.produto.getQuantidade() > 0){
				this.produto.setQuantidade(this.produto.getQuantidade()-1);
				carrinhoBean.getCarrinho().add(produto);
			}			
			rota("../Pages/listagemProdutos.xhtml");
		}
		this.produto = new Produto();
	}
}
