# Biblioteca Spring Boot CRUD

Aplicação didática em Spring Boot para um sistema de biblioteca.

## Serviços internos

- Cliente: mantém os dados dos clientes.
- Livro: mantém os dados dos livros e o estoque disponível.
- Empréstimo: executa a regra de negócio do empréstimo e da devolução.
- Biblioteca: orquestra os pedidos do cliente e retorna mensagens amigáveis.

## Executar

```bash
mvn spring-boot:run
```

A aplicação executa em:

```text
http://localhost:8080
```

Console H2:

```text
http://localhost:8080/h2-console
```

JDBC URL:

```text
jdbc:h2:mem:bibliotecadb
```

## Testes via curl

### Criar cliente

```bash
curl -X POST http://localhost:8080/clientes \
-H "Content-Type: application/json" \
-d '{"nome":"Ana Silva","email":"ana@email.com","telefone":"62999990000"}'
```

### Criar livro

```bash
curl -X POST http://localhost:8080/livros \
-H "Content-Type: application/json" \
-d '{"titulo":"Clean Code","autor":"Robert C. Martin","isbn":"9780132350884","quantidadeDisponivel":2}'
```

### Realizar empréstimo

```bash
curl -X POST http://localhost:8080/biblioteca/emprestar \
-H "Content-Type: application/json" \
-d '{"clienteId":1,"livroId":1}'
```

### Listar empréstimos

```bash
curl http://localhost:8080/emprestimos
```

### Devolver livro

```bash
curl -X POST http://localhost:8080/biblioteca/devolver/1
```
