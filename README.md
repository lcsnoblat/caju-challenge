# Sistema de Autorização de Transações de Cartão de Crédito

## Descrição

Este projeto implementa um sistema de autorização de transações de cartão de crédito com base em uma série de regras de negócios, incluindo verificação de saldo em diferentes categorias (`FOOD`, `MEAL`, `CASH`) e priorização do nome do comerciante sobre o código MCC.

### Funcionalidades

- **Autorização Simples**: Verifica o saldo disponível na categoria mapeada pelo MCC para aprovar ou rejeitar a transação.
- **Fallback para CASH**: Se o saldo na categoria mapeada for insuficiente, verifica o saldo na categoria `CASH` e, se possível, realiza a transação.
- **Substituição de MCC por Nome de Comerciante**: Quando o MCC não é confiável, o nome do comerciante tem precedência e pode substituir o MCC para determinar a categoria correta.

## Estrutura do Projeto

- **Controllers**: Gerenciam as requisições HTTP e delegam as operações de negócio para os serviços.
- **Services**: Contêm a lógica de negócio, incluindo operações de autorização de transações e manipulação de saldos.
- **Repositories**: Interagem com o banco de dados MongoDB para persistência de dados.
- **Models**: Definem as entidades utilizadas no sistema, como `Account`, `Transaction`, e `BalanceTypes`.
- **Tests**: Incluem testes unitários utilizando JUnit e Mockito para verificar o comportamento dos serviços.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção de aplicações Java.
- **MongoDB**: Banco de dados NoSQL para persistência.
- **Docker**: Para containerização da aplicação.
- **JUnit e Mockito**: Para testes unitários.

## Como Executar

### Pré-requisitos

- **Docker**: Certifique-se de que o Docker está instalado e funcionando em sua máquina.
- **Maven**: Para construir o projeto, se você quiser rodar fora do Docker.
  Dockerização

A aplicação foi dockerizada para facilitar o setup e a execução. O Dockerfile define o processo de build e execução da aplicação, e o docker-compose.yml orquestra o MongoDB e a aplicação.

### Estrutura do Código

	•	src/main/java: Contém o código fonte da aplicação.
	•	/controller: Controladores REST que gerenciam as requisições HTTP.
	•	/service: Serviços que contêm a lógica de negócios.
	•	/model: Modelos de dados (entidades).
	•	/repository: Interfaces para acesso ao banco de dados.
	•	src/test/java: Contém os testes unitários.


POST /transactions/authorize: Autoriza uma transação de cartão de crédito.

```bash {
curl -X POST http://localhost:8080/transactions/authorize \
  -H 'Content-Type: application/json' \
  -d '{
    "accountId": "123456789",
    "totalAmount": 100.00,
    "mcc": "5812",
    "merchant": "PADARIA DO ZE               SAO PAULO BR"
  }'
}
```

Request Body:
```json {
{
    "accountId": "123456789",
    "totalAmount": 100.00,
    "mcc": "5812",
    "merchant": "PADARIA DO ZE               SAO PAULO BR"
}
```
Response Body:
```json {
{
  "code": "00"
}
```

Testes

Para rodar os testes unitários:
```bash {
mvn test
```

### Notas Finais

Optei por utilizar a abordagem de locks a nível de aplicação para garantir que apenas uma transação por conta seja processada por vez. O principal ponto positivo dessa abordagem é a facilidade de implementação, garantindo consistência nas transações com um esforço mínimo. No entanto, essa solução pode apresentar limitações em termos de escalabilidade, o que pode levar a aumentos na latência. Considerei o uso de message queues, como RabbitMQ ou Kafka, para processar as transações de forma assíncrona e ordenada, o que poderia melhorar a escalabilidade e reduzir o risco de contenção. Contudo, essa abordagem exigiria um tempo de implementação maior e adicionaria complexidade ao projeto, desviando-se da ideia inicial de criar uma solução rápida e funcional.