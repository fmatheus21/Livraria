<h1 align="center">API Livraria</h1>

</br></br>

Conteúdos
=================
<!--ts-->
   * [Sobre](#sobre)
   * [Features](#features)
   * [Pré-requisitos](#pre-requisitos)
   * [Instalação](#instalacao)     
   * [Como usar](#como-usar)  
       * [API](#api)      
   * [Tecnologias](#tecnologias)   
<!--te-->

</br></br></br></br>

<div id="sobre"></div>

# Sobre
<div>
<p align="left">Essa é uma pequena demonstração de uma API Rest de Livraria utilizando Spring, JPA e Swagger</p>
</div>

</br></br></br></br>

<h4 align="center"> 
🚧  Em construção...  🚧
</h4>

</br></br></br></br>


<div id="features"></div>

# Features
- [x] Cadastro de Autor
- [x] Excluir de Autor
- [x] Consultar Autor
- [x] Editar Autor
- [x] Listar Autores
- [x] Cadastro de Editora
- [x] Excluir de Editora
- [x] Consultar Editora
- [x] Editar Editora
- [x] Listar Editoras
- [x] Cadastro de Livro
- [x] Excluir de Livro
- [x] Consultar Livro
- [x] Editar Livro
- [x] Listar Livros

</br></br></br></br>


<div id="pre-requisitos"></div>

# Pré-requisitos
<p align="left">
  <ul>
    <li>Mysql 8</li>
    <li>JDK 12+</li>
    <li>Postman 7.36.1</li> 
    <li>Netbeans 12 ou IDE de sua escolha</li>    
    </ul>
  </p>


</br></br></br></br>

<div id="instalacao"></div>

# Instalação
<div>
<p align="left">Importe para o seu Mysql o arquivo <b>database.sql</b>, que se encontra em files. Na pasta files encontra-se também o arquivo <b>postman_collection.json</b> para que você importe para o seu postman. Esse arquivo contém os endpoints da aplicação. A API está documentada com Swagger.</p>
</div>

</br></br></br></br>

<div id="como-usar"></div>

# Como Usar

<div id="api"></div>

## API
<div>
<p align="left">Primeiramente, edite o arquivo <b>/api/src/main/resources/application.properties</b> e altere as seguintes informações:</p>  
  <ul>
  <li><b>server.port=</b>PORT - Porta do Mysql</li>
  <li><b>spring.datasource.username=</b>USER_NAME - Usuário do Mysql</li>
  <li><b>spring.datasource.password=</b>PASSWORD - Senha do Mysql</li>  
  </ul>  
 </br>
 <p align="left">Abra o projeto na sua IDE (originalmente desenvolvida no Netbeans) e execute. Abra o Postman e importe o arquivo <b>postman_collection.json</b> e faça os testes.</p>  
 </br>
<p align="left">A API está rodando em <b>http://localhost:8083/api</b></p>  
</div>

</br>

</br></br>

<div id="tecnologias"><div>

# Tecnologias 
<div>
<img src="https://img.shields.io/static/v1?label=Java&message=11&color=green"/>
<img src="https://img.shields.io/static/v1?label=spring-boot&message=2.4.3&color=green"/>
<img src="https://img.shields.io/static/v1?label=mysql&message=8.0.23&color=green"/>
<img src="https://img.shields.io/static/v1?label=swagger&message=2.6.0&color=green"/>  
<img src="https://img.shields.io/static/v1?label=devtools&message=2.4.3&color=green"/>
<img src="https://img.shields.io/static/v1?label=lombok&message=1.18&color=green"/>

</div>
