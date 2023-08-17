package br.com.ajax.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "nome")
	private String categoria;
	
	public Categoria() {
		
	}
	
	
	public Categoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCategoria() {
		return this.categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Long getId() {
		return id;
	}
	
}
