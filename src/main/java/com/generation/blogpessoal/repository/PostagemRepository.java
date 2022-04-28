package com.generation.blogpessoal.repository;
//classe você implemente métodos
//interface você assina métodos para que seja implementado em uma classe

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;

//criar repository depois criar herança da tabela postagem
//Jpa Repository (vem da biblioteca Jpa) é um classe que possui vários métodos
//Postagem é a classe que criamos e Long é referente ao ID (Para saber com qual tabela iremos trabalhar)
//depois disso ---> ir para classe controladora. 
@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long>{
	
	

}
