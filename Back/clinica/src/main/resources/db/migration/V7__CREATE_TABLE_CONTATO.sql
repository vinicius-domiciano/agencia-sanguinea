-- CRIANDO TABELA contato
CREATE TABLE contato (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_candidato BIGINT NOT NULL,
    ddd VARCHAR(2) NOT NULL,
    numero VARCHAR(9) NOT NULL,
    tipo_contato VARCHAR(8) NOT NULL,
    data_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT FK_CONTATO_CANDIDATO
        FOREIGN KEY (id_candidato)
        REFERENCES candidato (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);