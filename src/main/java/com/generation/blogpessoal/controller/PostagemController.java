package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController //1º para indicar que a classe é um controller
@RequestMapping ("/postagem") //2º para indicar o endereço HTML
@CrossOrigin (origins = "*", allowedHeaders = "*" ) //Serve para permitir requisições de servidores diferentes "*" de qqr lugar ou mudar * para específico
public class PostagemController {
	
	@Autowired //injeção de dependencia
	private PostagemRepository postagemRepository;
	
	@GetMapping //entidade de respota HTTP -> vai ter collection do tipo List (irmã do ArrayList)
	public ResponseEntity <List<Postagem>> getAll (){
		return ResponseEntity.ok(postagemRepository.findAll());
	}

}
