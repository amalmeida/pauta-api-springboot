# pauta-api-springboot
API REST votação pauta assembleia

SOLUÇÃO:
A API REST foi desenvolvida utilizando a linguagem Java, framework Spring Boot e Maven para controle de versão. 
Algumas dependencias do Spring Boot foram adicionadas no projeto como Hibernate, JPA e H2. O H2 é um banco de dados Open Source que funciona em memória, foi escolhido por ser de fácil configuração em projetos Spring Boot.
Os serviços REST desenvolvidos possuem endpoints com métodos dos tipos GET e POST, para criação e busca de dados. 


DESCRIÇÃO DA ATIVIDADE - OBJETO DE ESTUDO

Objetivo
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias,por votação. 
Imagine que você deve criar uma solução backend para gerenciar essas sessões de votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:
	● Cadastrar uma nova pauta
	● Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
	● Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
	● Contabilizar os votos e dar o resultado da votação na pauta 
	
Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que não infrinja direitos de uso).
É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.
