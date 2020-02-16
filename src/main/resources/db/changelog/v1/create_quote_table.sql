-- liquibase formatted sql

-- changeset admin:create_quote dbms:h2
create table QUOTE
(
    ISIN                  VARCHAR2(12) NOT NULL,
    BID                   NUMBER(10, 2),
    ASK                   NUMBER(10, 2) NOT NULL,
    ELVL                  NUMBER(10, 2),
    REV                   NUMBER(5),

    CONSTRAINT quote_pk PRIMARY KEY (ISIN)
);

CREATE UNIQUE INDEX UNQ_IND_ISIN ON QUOTE(ISIN);