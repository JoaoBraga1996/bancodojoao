🚀 Apresentando meu projeto: Banco do João 🏦💳

Estou empolgado em compartilhar o projeto "Banco do João", um sistema bancário que desenvolvi para demonstrar minhas habilidades em programação💻
O Banco do João é uma Webservice Java que simula as operações bancárias básicas, desde a criação de contas de clientes até transações financeiras e gestão de cartões de crédito e débito. O objetivo principal foi criar uma plataforma robusta e intuitiva, capaz de lidar com diversas situações do mundo real em um ambiente bancário.
Principais recursos do projeto incluem:
Cadastro de clientes com informações detalhadas, como CPF, endereço e dados de contato.
Gerenciamento de contas, permitindo a criação de contas correntes e poupança associadas aos clientes.
Funcionalidades avançadas de cartões, incluindo cartões de crédito e débito com diferentes limites e políticas de utilização.
Implementação de seguros para os cartões, como seguro contra furto e seguro de vida.
Transferências e pagamentos entre contas, incluindo a possibilidade de realizar transferências PIX.
Manutenção automática de dados, como geração de números de cartões e apólices de seguro.

🏦💳 Integração usando API externa ViaCEP 🚀
Integração com o Feign: Utilizamos o Feign para integrar com uma API externa, neste caso, a API ViaCEP, que fornece informações de endereços a partir do CEP.

🚀 Documentação com OpenAPI e Swagger! 🚀
Utilizando OpenAPI e Swagger para documentar e facilitar o desenvolvimento de APIs no meu projeto. Com essas ferramentas, consigo descrever de forma clara e padronizada os endpoints da minha API, especificando os métodos disponíveis, os parâmetros necessários e os tipos de resposta esperados. I

✨Validação dos Dados com Bean Validation e Anotações Personalizadas! ✨
Integrei o Bean Validation em minha aplicação e desenvolvi anotações personalizadas para garantir a validação eficiente dos dados. Uma dessas anotações, a ClienteInsertValid, foi criada para assegurar a exclusividade do CPF no cadastro de novos clientes.
Ao utilizar a anotação ClienteInsertValid em conjunto com o Bean Validation, alcancei um controle mais preciso sobre os dados inseridos, evitando duplicidades e mantendo a integridade do sistema.

📧 Utilizando o JavaMailSender para Simplificar o Envio de E-mails 📧
implementei o JavaMailSender em um projeto onde precisávamos enviar notificações importantes para os usuários de nossa plataforma. Com o JavaMailSender, pude configurar facilmente o envio de e-mails personalizados, garantindo que cada mensagem fosse entregue de forma rápida e precisa.

✨Gerenciamento Eficiente do Banco de Dados com Flyway ✨
Optei por integrar o Flyway para gerenciar de forma eficiente e robusta as migrações do banco de dados. Essa escolha permitiu uma gestão simplificada das alterações na estrutura do banco, garantindo consistência e facilitando a colaboração entre equipes de desenvolvimento.

🚀Utilizando o Spring Data JPA no meu projeto! 🚀
Com o Spring Data JPA, estou simplificando o mapeamento de entidades e realizando pesquisas de forma eficiente. Além disso, criei uma pesquisa personalizada para encontrar clientes pelo CPF. 

🚀Utilizando o padrão DTO (Data Transfer Object)🎉
Em poucas palavras, é uma classe simples que transporta dados entre diferentes componentes de um sistema. Ao invés de expor diretamente os detalhes da implementação dos objetos de domínio, o DTO fornece uma interface limpa e estruturada para transferir dados entre camadas da aplicação.

🚀 Destacando o Uso de Exceções Personalizadas em Meus Projetos 🚀
Uma das práticas que adoto é o uso de exceções personalizadas.
implementei exceções personalizadas em um projeto em que estou trabalhando, como a CepInvalidException e a ContaNotLinkedToClienteException entre outras. Essas exceções foram projetadas para capturar cenários específicos e garantir que os erros sejam tratados de forma clara e eficiente.
Ao utilizar exceções personalizadas, pude melhorar significativamente o gerenciamento de erros em meu código, fornecendo mensagens claras e informativas sobre o que deu errado e como corrigir o problema

📊 Destaque: Diagramas Claros e Intuitivos com Mermaid 📊
Utilizei a ferramenta Mermaid para criar diagramas claros e intuitivos. Com sua sintaxe simples e poderosa, pude visualizar e comunicar de forma eficaz a arquitetura, fluxos de trabalho e relacionamentos entre componentes do sistema

🚀 Demonstração de Testes Eficientes em Desenvolvimento Java

✨ ContaServiceTest:
Testes unitários para ContaService utilizando Mockito para simular dependências.
Validação da operação findAllPaged para resultados paginados.
Verificação do tratamento adequado das operações findByNumero, tanto para números existentes quanto não existentes.
Testes para garantir a integridade das operações de atualização e exclusão de contas, considerando diferentes cenários.
✨ ClienteServiceTest:

Testes unitários para ClienteService utilizando Mockito para simular dependências.
Validação da operação findAllPaged para resultados paginados.
Verificação da operação findById para IDs existentes e não existentes, garantindo o tratamento adequado de exceções.
Testes para assegurar a correta atualização e exclusão de clientes, incluindo tratamento de IDs dependentes.
✨ ClienteResourceTest:

Testes de integração usando @WebMvcTest para garantir o correto funcionamento do controlador REST.
Verificação das operações CRUD (Create, Read, Update, Delete) para clientes com diferentes cenários.
Utilização do Mockito para simular o comportamento do ClienteService e isolar os testes do controlador.
✨ Factory:

Fornece métodos de fábrica para criar instâncias de teste de Cliente e ClienteDTO, além de Conta e ContaDTO.
🔍 Tecnologias Utilizadas:

JUnit 5, Mockito, Spring Boot.
🌐 Propósito:

Assegurar a robustez e confiabilidade das operações no banco de dados e do controlador REST.
Demonstrar a implementação de testes unitários, de integração e de repositório em um ambiente Spring.

Apresentando o projeto parte 1 -> https://www.youtube.com/watch?v=fGXzaVOxdZk
parte 2 ->  https://www.youtube.com/watch?v=WS4wiDeO3S8
