package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
		// select * from tb_postagem
	}
	
	//função lambda (ao invés de IF)
	@GetMapping ("/{id}") //significa que depois de postagem vou buscar o id
	public ResponseEntity <Postagem> getById(@PathVariable long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		
		// select * from tb_postagem where id = id
	}
	
	@GetMapping("/titulo/{titulo}") 
	public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		
		// select * from tb_postagem where titulo = %titulo%
	}
	
	@PostMapping
	public ResponseEntity <Postagem> postPostagem (@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
		
		//Insert
		
	}
		
	@PutMapping ("/{id}")
	public ResponseEntity<Postagem> putPostagem (@PathVariable long id, @RequestBody Postagem postagem){
		Postagem postagemModificada = postagemRepository.findById(id).get();
		BeanUtils.copyProperties(postagem, postagemModificada, "id");
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagemModificada));
				
				
		
		//Alterar 
	
	}  
	
	@DeleteMapping ("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)//Para trazer o status sem conteúdo
	public void deletePostagem (@Valid @PathVariable Long id){
		postagemRepository.deleteById(id); //chamo o método postagemRepository
		
	}	
	
	
	
	
	
	
	
	
	
	
}
