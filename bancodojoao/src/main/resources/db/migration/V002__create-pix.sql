CREATE TABLE TRANSFERENCIAPIX (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_conta_origem BIGINT NOT NULL,
    id_conta_destino BIGINT NOT NULL,
    valor DECIMAL(18, 2) NOT NULL,
    data_transferencia TIMESTAMP NOT NULL,
    FOREIGN KEY (id_conta_origem) REFERENCES Conta(id),
    FOREIGN KEY (id_conta_destino) REFERENCES Conta(id)
);
