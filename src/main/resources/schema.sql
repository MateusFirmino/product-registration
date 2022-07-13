--DROP TABLE PRODUTO;
CREATE TABLE IF NOT EXISTS PRODUTO
(
    ID           int  NOT NULL AUTO_INCREMENT,
    NOME         varchar(255),
    PRECO        decimal,
    QUANTIDADE   integer,
    DATA_CRIACAO date NOT NULL,
    PRIMARY KEY (ID)
);





