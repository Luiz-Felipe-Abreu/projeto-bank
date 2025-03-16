package com.projetobank.controller;

import com.projetobank.model.ContaBancaria;
import com.projetobank.repository.ContaBancariaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaBancariaController {

    private final ContaBancariaRepository repository;

    public ContaBancariaController(ContaBancariaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ContaBancaria> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public Optional<ContaBancaria> buscarPorCpf(@PathVariable String cpf) {
        return repository.findByCpf(cpf);
    }

    @PostMapping
    public ContaBancaria cadastrarConta(@RequestBody ContaBancaria conta) {
        return repository.save(conta);
    }

    @PutMapping("/encerrar/{id}")
    public ResponseEntity<String> encerrarConta(@PathVariable Long id) {
        ContaBancaria conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        conta.setAtiva(false);
        repository.save(conta);
        return ResponseEntity.ok("Conta encerrada com sucesso!");
    }

    @PostMapping("/saque")
    public ResponseEntity<String> sacar(@RequestParam Long id, @RequestParam double valor) {
        ContaBancaria conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        if (conta.getSaldo() < valor) {
            return ResponseEntity.badRequest().body("Saldo insuficiente para saque.");
        }
        conta.setSaldo(conta.getSaldo() - valor);
        repository.save(conta);
        return ResponseEntity.ok("Saque realizado com sucesso!");
    }

    @PostMapping("/pix")
    public ResponseEntity<String> transferirPix(@RequestParam Long idOrigem, @RequestParam Long idDestino, @RequestParam double valor) {
        ContaBancaria origem = repository.findById(idOrigem)
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada!"));
        ContaBancaria destino = repository.findById(idDestino)
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada!"));
        if (origem.getSaldo() < valor) {
            return ResponseEntity.badRequest().body("Saldo insuficiente para transferência!");
        }
        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);
        repository.save(origem);
        repository.save(destino);
        return ResponseEntity.ok("Transferência realizada com sucesso!");
    }
}
