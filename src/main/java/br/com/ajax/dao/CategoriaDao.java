package br.com.ajax.dao;

import javax.persistence.EntityManager;

import br.com.ajax.modelo.Categoria;

public class CategoriaDao {

	
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	public void cadastrarCategoria(Categoria categoria) {
		em.persist(categoria);
	}
	public void atualizarCategoria(Categoria categoria) {
		categoria = em.merge(categoria);
	}
	public void deletarCategoria(Categoria categoria) {
		categoria = this.em.merge(categoria);
	}
	public Categoria getCategoria(Long id) {
		return em.find(Categoria.class, id);
	}
}
