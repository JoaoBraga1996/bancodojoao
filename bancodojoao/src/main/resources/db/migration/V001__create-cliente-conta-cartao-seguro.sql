  create table cartao (
        limite_credito numeric(38,2),
        limite_diario numeric(38,2),
        limite_diario_utilizado numeric(38,2),
        limite_utilizado numeric(38,2),
        status tinyint check (status between 0 and 1),
        conta_id bigint,
        id bigint generated by default as identity,
        tipo_cartao varchar(31) not null,
        numero varchar(255),
        senha varchar(255),
        primary key (id)
    );

    create table cliente (
        categoria tinyint check (categoria between 0 and 2),
        atualizado_em timestamp(6) with time zone,
        criado_em timestamp(6) with time zone,
        id bigint generated by default as identity,
        bairro varchar(255),
        cep varchar(255),
        complemento varchar(255),
        cpf varchar(255),
        email varchar(255),
        localidade varchar(255),
        nome varchar(255),
        numero varchar(255),
        rua varchar(255),
        senha varchar(255),
        uf varchar(255),
        primary key (id)
    );
    create table conta (
        saldo numeric(38,2),
        taxa_manutecao numeric(38,2),
        taxa_rendimento numeric(38,2),
        cliente_id bigint not null,
        criado_em timestamp(6) with time zone,
        id bigint generated by default as identity,
        tipo_conta varchar(31) not null,
        agencia varchar(255),
        numero varchar(255),
        primary key (id)
    );

    create table seguro (
    valor_apolice numeric(38,2),
    cartao_id bigint not null,
    data_contratacao timestamp(6) with time zone,
    id bigint generated by default as identity,
    tipo_seguro varchar(31) not null,
    descricao_condicao varchar(255),
    item_coberto varchar(255),
    nome_beneficiario varchar(255),
    numero_apolice varchar(255),
    primary key (id)
);