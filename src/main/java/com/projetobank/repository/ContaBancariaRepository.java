package com.projetobank.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projetobank.model.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
    Optional<ContaBancaria> findByCpf(String cpf);
}
