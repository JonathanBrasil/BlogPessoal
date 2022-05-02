package com.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// 1st criando a tabela tema...
// Criando atributos da tabela...
// Criando Get e Setters...
// Criar chave estrangeira one to many

@Entity
@Table (name = "tb_temas")

public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "O atributo Descrição é obrigatório e não pode conter espaços em branco")
	private String descricao;
	
	/* OneToMany indica que a classe tema irá relacionar com a classe postagem 1 -> pra -> muitos
	 * cascade Type.remove indica que ao apagar um tema irá apagar postagens relacionadas 
	 * JsonIgnore desabilita o looping infinito
	 */
	
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE) 
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem; //collection do tipo lista que irá trazer as postagens

	public Long getId() {
		return id;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
