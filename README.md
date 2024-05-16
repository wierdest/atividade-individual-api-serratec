Reposítório para a Atividade Individual de avaliação na disciplina de WebAPI do Serratec.

# Uma pequena API de pedidos para um restaurante oriental "SaborZen".

## É feita em Java, usando Spring Boot e PostgreSQL.

### Importar o Maven project em uma IDE deve ser suficiente para testes.

### Para a conexão com o database, deve-se criar um database chamado "saborzen" (sem aspas).
### Também é necessária a utilização da váriavel de ambiente POSTGRES_PASSWORD para a senha.

### A API tem operações básicas de CRUD, usando apenas uma entidade, "Pedido":

### Exemplo de POST request body para o Postman:

    {
        "valor": 14.9,
        "cliente": "André",
        "descricao": "Temaki",
        "data": "2024-03-28"
    }

### É possível requisitar pedidos pelo nome do cliente, usando uma query simples no Postman. 
### Tal request é feita usando *findByClienteContainingIgnoreCase*, um JPA Query Method que permite a pesquisa por parte da string, ignorando a capitulação.

### Também é possível requisitar pedidos pelo nome do cliente, ao mesmo tempo passando um valor limite, para que seja possível encontrar pedidos acima de tal valor.
### Tal request é feita usando *findByClienteAndValorGreaterThan*, mais um JPA Query Method que permite a pesquisa por cliente e por valores acima do informado.
### Para executar essa request foi criado um DTO auxiliar, PedidoClienteMaisCaroDTO.

## Há um tratamento de erros básico para impedir a inserção de campos nulos ou vazios.
     {
        "valor": ,
        "cliente": "",
        "descricao": "",
        "data": ""
    }

### A request acima geraria a resposta:

[
    "cliente - Relaxa e medita! O nome do cliente não pode ser em branco!",
    "descricao - A descrição tem que ser maior do que 3 caracteres!",
    "descricao - must not be blank",
    "data - Tempo pode ser uma ilusão, mas a data do pedido não pode ser nula!!!",
    "cliente - O nome do cliente tem que ter mais de 3 caracteres!"
]

### Tal tratamento de erro é feito a partir da classe PedidoCOntrollerHandler, que faz um Override no método *handleMethodArgumentNotValid*


