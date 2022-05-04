package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

@RestController //1º para indicar que a classe é um controller
@RequestMapping ("/postagem") //2º para indicar o endereço HTML
@CrossOrigin (origins = "*", allowedHeaders = "*" ) //Serve para permitir requisições de servidores diferentes "*" de qqr lugar ou mudar * para específico
public class PostagemController {
	
	@Autowired //injeção de dependencia
	private PostagemRepository postagemRepository;
	
	@Autowired //injeção de dependencia para a classe tema
	private TemaRepository temaRepository;
	
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
		
		if (temaRepository.existsById(postagem.getTema().getId()))//Se existir, pega o tema depois postagem
			return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //caso não retorna msg de erro
		
		//Criar postagem somente com tema existente. 
		
	}
		
	@PutMapping
	
	/* TENTEI DE TODAS AS FORMAS FAZER ISSO FUNCIONAR COM O .MAP mas faltou isso aqui: findById(postagem.getId())
	Apelei para o arquivo do professor mas pelo menos entendi o que faltava pois no exemplo abaixo estava pegando
	.get () tudo que estava na pag */
	
	//Tentar implementar a parte de atualizar somente os dados que precisarem...
	
	public ResponseEntity<Postagem> putPostagem (@Valid @RequestBody Postagem postagem){
		if (postagemRepository.existsById(postagem.getId())) {
			
			if (temaRepository.existsById(postagem.getTema().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		
			/* return 
			.map(resposta -> ResponseEntity.ok().body(postagemRepository.save(postagem)))
			.orElse(ResponseEntity.notFound().build()); */

			//Alterar método Put
				
	}  		
	
		
	@DeleteMapping ("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)//Para trazer o status sem conteúdo
	public void deletePostagem (@Valid @PathVariable Long id){
		Optional<Postagem> postagem = postagemRepository.findById(id); //como se fosse map
		
		if (postagem.isEmpty()) //checagem se é vazio...
			throw new ResponseStatusException(HttpStatus.NOT_FOUND); //caso não encontre "joga" uma nova resposta	
		
		postagemRepository.deleteById(id); //chamo o método postagemRepository
		
		//Deletar método Delete
	}	
		
		/*
		 * OUTRA ALTERNATIVA PARA O DELETE
		 * @DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable Long id) {
		
		return postagemRepository.findById(id)
				.map(resposta -> {
					postagemRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
		 */
		
	
	
	
	
	
	
	
	
	
	
	
}
