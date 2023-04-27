package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.service.PagamentoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping
    public Page<PagamentoDto> obterTodosPagamentos (@PageableDefault(size = 10) Pageable paginacao)  {
        return pagamentoService.obterTodosPagamentos(paginacao);
    }

    @GetMapping("/{idPagamento}")
    public ResponseEntity<PagamentoDto> obterPagamentoPorId (@PathVariable @NotNull Long idPagamento)  {
        PagamentoDto response = pagamentoService.obterPagamentoPorId(idPagamento);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> criarPagamento (@RequestBody @Valid PagamentoDto pagamentoDto, UriComponentsBuilder uriComponentsBuilder)  {
        PagamentoDto response = pagamentoService.criarPagamento(pagamentoDto);
        URI endereco = uriComponentsBuilder.path("/pagamentos/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(endereco).body(response);
    }

    @PutMapping("/{idPagamento}")
    public ResponseEntity<PagamentoDto> atualizarPagamento (@RequestBody @Valid PagamentoDto pagamentoDto, @PathVariable Long idPagamento)  {
        PagamentoDto response = pagamentoService.atualizarPagamento(idPagamento, pagamentoDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idPagamento}")
    public ResponseEntity<PagamentoDto> deletarPagamento (@PathVariable @NotNull Long idPagamento)  {
        pagamentoService.deletarPagamento(idPagamento);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirmar")
    @CircuitBreaker(name = "atualizaPedido", fallbackMethod = "pagamentoAutorizadoComIntegracaoPendente")
    public void confirmarPagamento(@PathVariable @NotNull Long id){
        pagamentoService.confirmarPagamento(id);
    }

    public void pagamentoAutorizadoComIntegracaoPendente(Long id, Exception e){
        pagamentoService.alteraStatus(id);
    }
}
