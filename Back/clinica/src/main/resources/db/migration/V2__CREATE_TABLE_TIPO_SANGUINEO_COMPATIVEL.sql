-- Criando tabela de tipo sanguineo compatibilidade
CREATE TABLE tipo_sanguineo_compativel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_sanguineo BIGINT NOT NULL,
    compatibilidade VARCHAR(8) NOT NULL,
    tipo VARCHAR(8) NOT NULL,
    data_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT FK_TIPO_SANGUINEO_COMPATIVEL
        FOREIGN KEY (id_tipo_sanguineo)
        REFERENCES tipo_sanguineo (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);