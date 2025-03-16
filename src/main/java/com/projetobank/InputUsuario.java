package com.projetobank;

import java.util.Scanner;
import java.time.LocalDate;
import com.projetobank.model.ContaBancaria;
import com.projetobank.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InputUsuario implements CommandLineRunner {

    @Autowired
    private ContaBancariaService service;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Cadastro de Conta Bancária");

            String numeroConta;
            do {
                System.out.print("Número da conta: ");
                numeroConta = scanner.nextLine();
            } while (numeroConta.isEmpty());

            String agencia;
            do {
                System.out.print("Agência: ");
                agencia = scanner.nextLine();
            } while (agencia.isEmpty());

            String titular;
            do {
                System.out.print("Nome do Titular: ");
                titular = scanner.nextLine();
            } while (titular.isEmpty());

            String cpf;
            do {
                System.out.print("CPF do Titular: ");
                cpf = scanner.nextLine();
            } while (cpf.isEmpty());

            LocalDate dataAbertura = LocalDate.now();

            double saldo;
            do {
                System.out.print("Saldo Inicial: ");
                saldo = scanner.nextDouble();
                scanner.nextLine();
            } while (saldo < 0);

            String tipoConta;
            do {
                System.out.print("Tipo de Conta (CORRENTE, POUPANCA, SALARIO): ");
                tipoConta = scanner.nextLine().toUpperCase();
            } while (!tipoConta.equals("CORRENTE") && !tipoConta.equals("POUPANCA") && !tipoConta.equals("SALARIO"));

            ContaBancaria conta = new ContaBancaria(null, numeroConta, agencia, titular, cpf, dataAbertura, saldo, true, tipoConta);
            service.cadastrarConta(conta);

            System.out.println("Conta cadastrada com sucesso!");
            System.out.print("Deseja cadastrar outra conta? (S/N): ");
            String resposta = scanner.nextLine().toUpperCase();
            continuar = resposta.equals("S");
        }
        scanner.close();
    }
}
