Java: 8

por utf-8 e java jdk 8 na ide, no meu caso, usei SpringTools.

maven -> update project

rode localmente como run/debug

Swagger local: http://localhost:8080/swagger-ui/index.html#/

usuário: admin
senha: admin

se faz necessário rodar, pelo menos 1x, no postman em authorizathion -> basic auth o usuário e a senha acima, para autenticar e conseguir fazer as requests abaixo

curls de cada endpoint para importar no postman:

1° cadastrarBeneficiario
curl --location 'http://localhost:8080/api/beneficiarios/' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "João Silva",
    "telefone": "123456789",
    "dataNascimento": "1990-01-01",
    "dataInclusao": "2024-08-04",
    "documentos": [
        {
            "tipoDocumento": "CPF",
            "descricao": "Cadastro de Pessoa Física",
            "numero": "20187680000",
            "dataInclusao": "2024-08-04",
            "dataAtualizacao": "2024-08-04"
        }
    ]
}'

2° listarTodos:
curl --location 'http://localhost:8080/api/beneficiarios/'

3° obterBeneficiarioPorId
curl --location 'http://localhost:8080/api/beneficiarios/1'

4° obterDocumentosPorIdBeneficiario
curl --location 'http://localhost:8080/api/beneficiarios/1/documentos'

5° atualizarBeneficiario
curl --location --request PUT 'http://localhost:8080/api/beneficiarios/1' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "João Silva",
    "telefone": "123456789",
    "dataNascimento": "1990-01-01",
    "dataInclusao": "2024-08-04",
    "dataAtualizacao": "2024-08-05",
    "documentos": [
        {
            "id": 1,
            "tipoDocumento": "CPF",
            "descricao": "Cadastro Pessoa Física",
            "dataInclusao": "2024-08-04",
            "dataAtualizacao": "2024-08-04"
        }
    ]
}'

6° removerBeneficiario
curl --location --request DELETE 'http://localhost:8080/api/beneficiarios/1'