package br.com.minhaloja.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.minhaloja.model.Produto;

@Named(value="carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Produto> carrinho;
	
	@Inject
	private ProdutoBean produtoBean;
	
	@PostConstruct
	public void inicializar(){
		carrinho = new ArrayList<Produto>();
	}
	
	public List<Produto> getCarrinho(){
		return carrinho;
	}
	
	public Double calcularValorTotal(){
		
		Double total = 0D;
		
		for (Produto produto : getCarrinho()) {
			total += produto.getPreco();
		}
		
		return total;
	}
	
	public int totalProdutos(){
		int total = this.carrinho.size();
		return total; 
	}
	
	public void removerItem(Produto produto){
		if(produtoBean.getProdutos().contains(produto)){
			produtoBean.setProduto(produto);			
			produtoBean.getProduto().setQuantidade(produtoBean.getProduto().getQuantidade()+1);
		}
		this.carrinho.remove(produto);		
	}
	

}
