package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

//importando entidade javax (Que vai permitir fazer as anotações nos atributos da tabela)
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //equivalente ao creat table (MySql)
@Table(name = "tb_postagens") //para definir nome da tabela dentro do DB
public class Postagem {
	
	
	@Id //para indicar classe primária || primary key id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //para indicar autoincremento
	private Long id ; //com L maisculo + recurso
	
	@NotBlank(message = "Obrigatório") //indicar o Notnull
	@Size(min = 5, max =100, message = "o Título deve ter no mínimo 5 e no máximo 100 caracteres")//para definir tamanho min e/ou max 
	private String titulo;
	
	@NotNull(message = "Obrigatório")//permite espaços em branco
	@Size(min = 5, max =1000, message = "o texto deve ter no mínimo 10 e no máximo 1000 caracteres")
	private String texto;
	
	@UpdateTimestamp //vai receber do sistema a data e hr toda vez que fizer uma ação (ou creat para somente na criação).
	private LocalDateTime data;

//CHAVES ESTRANGEIRAS	
	
	@ManyToOne //tipo de relacionamento da classe
	@JsonIgnoreProperties("postagem")
	private Tema tema; //CHAVE ESTRANGEIRA
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
//métodos getters e setters:
	
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	} 
	
	
	
	//após criar os atributos, criar os getters e setters
	//Source > creat 
	

}
