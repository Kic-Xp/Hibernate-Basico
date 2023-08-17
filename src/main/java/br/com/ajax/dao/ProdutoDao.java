package br.com.ajax.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.ajax.modelo.Produto;

public class ProdutoDao {

	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	public void cadastrarProduto(Produto produto) {
		this.em.persist(produto);
	}
	public void atualizarProduto(Produto produto) {
		this.em.merge(produto);
	}
	public void deletarProduto(Produto produto) {
		produto = this.em.merge(produto);
		this.em.remove(produto);
	}
	public Produto buscarProduto(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		String jpql = "select p from Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	public List<Produto> buscarPorNome(String nome){
		String jpql = "select p from Produto p where p.nome like :nome";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome).getResultList();
	}
	public List<Produto> buscarPorCategoria(String nome){
		String jpql = "select p from Produto p where p.categoria.categoria = :nome";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome).getResultList();
	}
	public BigDecimal buscarPrecoPorNome(String nome){
		String jpql = "select p.preco from Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome)
				.getSingleResult();
	}
}
