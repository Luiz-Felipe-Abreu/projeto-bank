package com.projetobank.service;

import com.projetobank.model.ContaBancaria;
import com.projetobank.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository repository;

    public List<ContaBancaria> listarTodas() {
        return repository.findAll();
    }

    public Optional<ContaBancaria> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<ContaBancaria> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public ContaBancaria cadastrarConta(ContaBancaria conta) {
        if (conta.getTitular() == null || conta.getCpf() == null || conta.getSaldo() < 0) {
            throw new IllegalArgumentException("Dados inválidos para cadastro de conta.");
        }
        if (conta.getDataAbertura().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de abertura não pode ser no futuro.");
        }
        return repository.save(conta);
    }

    public ContaBancaria depositar(Long id, double valor) {
        ContaBancaria conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        conta.setSaldo(conta.getSaldo() + valor);
        return repository.save(conta);
    }

    public ContaBancaria sacar(Long id, double valor) {
        ContaBancaria conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        if (conta.getSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente para saque.");
        }
        conta.setSaldo(conta.getSaldo() - valor);
        return repository.save(conta);
    }

    public void realizarPix(Long idOrigem, Long idDestino, double valor) {
        ContaBancaria origem = repository.findById(idOrigem)
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada!"));
        ContaBancaria destino = repository.findById(idDestino)
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada!"));
        if (origem.getSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente para transferência.");
        }
        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);
        repository.save(origem);
        repository.save(destino);
    }

    public void encerrarConta(Long id) {
        ContaBancaria conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        conta.setAtiva(false);
        repository.save(conta);
    }
}
