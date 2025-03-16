O projeto Bank app é uma aplicação em Spring Boot que simula um sistema bancário, com funcionalidades de cadastro de conta bancaria 
Ele inclui funcionalidades essenciais como cadastro de contas, consultas, depósitos, saques, transferências via PIX e encerramento de contas.

Model (Modelo) - Representação dos Dados
Define a estrutura de uma conta bancária com atributos como:

Número da conta
Agência
Titular
CPF
Saldo
Tipo de conta (Corrente, Poupança, Salário)
Status (ativa ou inativa)

Repository (Repositório) - Acesso ao Banco de Dados
Interface que se comunica com o banco de dados e permite o CRUD (Create, Read, Update, Delete).

Service (Serviço) - Regra de Negócio
Contém todas as regras de negócio da aplicação, incluindo:

Cadastro de contas
Depósitos e saques
Transferências entre contas (PIX)
Encerramento de contas
Validações (exemplo: saldo não pode ser negativo)

Endpoints
Listar todas as contas	http://localhost:8080/contas
Buscar conta por ID	http://localhost:8080/contas/{id}
Buscar conta por CPF	http://localhost:8080/contas/cpf/{cpf}
Criar nova conta	http://localhost:8080/contas
Encerrar conta	http://localhost:8080/contas/encerrar/{id}
Realizar saque	http://localhost:8080/contas/saque?id={id}&valor={valor}
Realizar transferência (PIX)	http://localhost:8080/contas/pix?idOrigem={idOrigem}&idDestino={idDestino}&valor={valor}





