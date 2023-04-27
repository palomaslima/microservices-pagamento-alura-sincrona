CREATE TABLE pagamentos (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 valor decimal(19,2) NOT NULL,
 nome_cliente varchar(100) DEFAULT NULL,
 numero_cartao varchar(19) DEFAULT NULL,
 expiracao_cartao varchar(7) DEFAULT NULL,
 codigo_cartao varchar(3) DEFAULT NULL,
 status varchar(255) NOT NULL,
 forma_de_pagamento_id bigint(20) NOT NULL,
 pedido_id bigint(20) NOT NULL,
PRIMARY KEY (id)
);