package br.com.alurafood.pagamentos.dto;

import br.com.alurafood.pagamentos.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoDto {

    private Long id;
    private BigDecimal valor;
    private String nomeCliente;
    private String numeroCartao;
    private String expiracaoCartao;
    private String codigoCartao;
    private Status status;
    private Long pedidoId;
    private Long formaDePagamentoId;
}
