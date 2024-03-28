CREATE TABLE compra_realizada (
    id UUID PRIMARY KEY,
    id_cartao_origem BIGINT NOT NULL,
    valor NUMERIC(19,2) NOT NULL,
    data_compra TIMESTAMP NOT NULL
);
