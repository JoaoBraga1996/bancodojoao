insert into cliente 
(atualizado_em, categoria, cpf, criado_em, email, bairro, cep, complemento, localidade, numero, rua, uf, nome, senha, data_aniversario) 
values 
(current_timestamp, 1, '123.456.789-00', current_timestamp, 'exemplo@email.com', 'Centro', '37820-000', 'Apto 101', 'Arceburgo', '123', 'Rua Exemplo', 'MG', 'Fulano de Tal', 'senha123', '1990-04-12');

insert into cliente 
(atualizado_em, categoria, cpf, criado_em, email, bairro, cep, complemento, localidade, numero, rua, uf, nome, senha, data_aniversario) 
values 
(current_timestamp, 0, '987.654.321-00', current_timestamp, 'outroexemplo@email.com', 'Vila Nova', '37820-000', 'Casa', 'Arceburgo', '456', 'Rua Nova', 'MG', 'Beltrano da Silva', 'outrasenha456', '1970-03-14');


-- Inserts para a tabela 'conta'
insert into conta (agencia, cliente_id, criado_em, numero, saldo, taxa_manutecao, tipo_conta) 
values ('00001', 1, current_timestamp, '123456', 1000.00, 5.00, 'corrente');

insert into conta (agencia, cliente_id, criado_em, numero, saldo, taxa_rendimento, tipo_conta) 
values ('00003', 1, current_timestamp, '135792', 5000.00, 0.005, 'poupanca');

insert into conta 
(agencia, cliente_id, criado_em, numero, saldo, taxa_manutecao, tipo_conta) 
values 
('00123', 2, current_timestamp, '98765432-1', 5000.00, 10.00, 'corrente');


-- Inserts para a tabela 'cartao'
insert into cartao (conta_id, numero, senha, status, limite_diario, limite_diario_utilizado, tipo_cartao) 
values (1, '1234 5678 9012 3456', 'senha123', 1, 200.00, 50.00, 'debito');

insert into cartao (conta_id, numero, senha, status, limite_diario, limite_diario_utilizado, limite_credito, limite_utilizado, tipo_cartao) 
values (2, '9876 5432 1098 7654', 'senha456', 1, 1000.00, 1000.00, 1000.00, 200.00, 'credito');

-- Inserts para a tabela 'seguro'
insert into seguro (cartao_id, data_contratacao, descricao_condicao, numero_apolice, valor_apolice, nome_beneficiario, tipo_seguro) 
values (2, current_timestamp, 'Seguro de vida para o titular', '12345', 50000.00, 'João Silva', 'vida');

insert into seguro (cartao_id, data_contratacao, descricao_condicao, numero_apolice, valor_apolice, item_coberto, tipo_seguro) 
values (2, current_timestamp, 'Seguro contra furto ou roubo', '54321', 10000.00, 'Smartphone', 'furto');
