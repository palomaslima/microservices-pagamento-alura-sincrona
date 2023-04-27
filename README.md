# microservices-pagamento-alura-sincrona
Microserviços para projeto de Pagamentos utilizando Eureka, Gateway, MySql, Feign Clients e Resilience4J

## Pedidos
Microserviço que controla todos os pedidos, possui um crud para cadastro, atualização, consulta e exclusão de pedidos.

## Pagamento
Microserviço que controla todos os pagamentos dos pedidos, possui um crud para cadastro, atualização, consulta e exclusão de pagamentos. Também possui uma requisição para confirmação de pagamentos que atualiza o pedido.

Todos os microserviços são cadastrados no Eureka Server e possuem um gateway para tudo ficar em uma mesma url para requisição.

<ul>
<li>Java 17 com Maven</li>
<li>Springboot</li>
<li>Lombok para Getters,Setters</li>
<li>JPA para conexão com banco de dados</li>
<li>MySQL</li>
<li>FlyWay para versionamento de banco de dados</li>
<li>Feign Client para comunicação síncrona entre microserviços</li>
<li>Resilience4J com CircuitBreaker e Fallback para tolerância a falhas</li>
</ul>

