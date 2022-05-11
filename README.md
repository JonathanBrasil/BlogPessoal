<h1 align="center">
 <img src="https://devkico.itexto.com.br/wp-content/uploads/2014/08/spring-boot-project-logo.png" width="100">
 <p align="center">Projeto de criação Blog Pessoal </p>
</h1>

<h2 Align="left"> Back-end </h2>



- Usando Spring boot para criar uma API RESTful para nosso blog pessoal.
- SpringTool + MySQL e testando no Insomnia
<br>
<h3 align="center">
DER: Modelo de Entidade-Relacionamento desse projeto:

```mermaid
classDiagram
class Tema {
  - id : Long
  - descricao : String
  - postagem : List ~Postagem~
  + getAll()
  + getById(Long id)
  + getByDescricao(String descricao)
  + postTema(Tema tema)
  + putTema(Tema tema)
  + deleteTema(Long id)
}
class Postagem {
  - id : Long
  - titulo : String
  - texto: String
  - midia: String
  - data: LocalDateTime
  - tema : Tema
  - usuario : Usuario
  + getAll()
  + getById(Long id)
  + getByTitulo(String titulo)
  + postPostagem(Postagem postagem)
  + putPostagem(Postagem postagem)
  + deleteTema(Long id)
}
class Usuario {
  - id : Long
  - nome : String
  - usuario : String
  - senha : String
  - foto : String
  - bio : String
  - postagem : List ~Postagem~
  + getAll()
  + getById(Long id)
  + autenticarUsuario(UsuarioLogin usuarioLogin)
  + cadastrarUsuario(Usuario usuario)
  + atualizarUsuario(Usuario usuario)
}
class UsuarioLogin{
  - id : Long
  - nome : String
  - usuario : String
  - senha : String
  - foto : String
  - bio : String
  - token : String
}
Tema --> Postagem
Usuario --> Postagem
```  
  
</h3>
<br>
<h4 align="center"> 
	🚧  API 🚀 Em construção...  🚧
</h4>
