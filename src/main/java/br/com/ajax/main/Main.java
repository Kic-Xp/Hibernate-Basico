package br.com.ajax.main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.ajax.dao.CategoriaDao;
import br.com.ajax.dao.ProdutoDao;
import br.com.ajax.modelo.Categoria;
import br.com.ajax.modelo.Produto;
import br.com.ajax.util.JPAUtil;

public class Main {
	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getFactory();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarProduto(1l);
		System.out.println(produto.getPreco());
		List<Produto> celulares = produtoDao.buscarPorNome("camiseta");
		System.out.println(celulares.size());
		System.out.println(celulares.get(0).getNome());
		List<Produto> produtos = produtoDao.buscarTodos();
		for (Produto p : produtos) {
			System.out.println(p.getId());
			System.out.println(p.getNome());
			System.out.println(p.getDescricao());
			System.out.println(p.getPreco());
			System.out.println("----------------------------------------------");
		}
		List<Produto> categorias = produtoDao.buscarPorCategoria("Celular");
		for (Produto p : categorias) {
			System.out.println(p.getNome());
		}
		BigDecimal preco = produtoDao.buscarPrecoPorNome("camiseta");
		System.out.println(preco);
	}

	private static void cadastrarProduto() {
		
		
		Categoria roupas = new Categoria("Roupa");
		Categoria celulares = new Categoria("Celular");
		
		Produto camisa = new Produto("camiseta", "Camiseta vermelha com listras", 
				new BigDecimal("50"),roupas);
		
		Produto shortj = new Produto("short", "Short preto", new BigDecimal("20"),roupas);
		Produto blusa = new Produto("blusa", "Blusa preta", new BigDecimal("30"),roupas);
		Produto celular = new Produto("celular", "celular preto", new BigDecimal("30"),celulares);
		EntityManager em = JPAUtil.getFactory();

		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		

		em.getTransaction().begin();
		
		categoriaDao.cadastrarCategoria(celulares);
		categoriaDao.cadastrarCategoria(roupas);
		produtoDao.cadastrarProduto(camisa);
		em.flush();
		produtoDao.cadastrarProduto(shortj);
		produtoDao.cadastrarProduto(blusa);
		produtoDao.cadastrarProduto(celular);
		em.getTransaction().commit();
		em.close();
		System.out.println(camisa.getId());
		System.out.println(roupas.getId());
	}
}
