-- liquibase formatted sql

-- changeset admin:create_history dbms:h2
create table HISTORY
(
    ID                    NUMBER(10, 0) NOT NULL IDENTITY,
    ISIN                  VARCHAR2(12) NOT NULL,
    BID                   NUMBER(10, 2) NOT NULL,
    ASK                   NUMBER(10, 2) NOT NULL,
    CREATE_TIME           TIMESTAMP(6)  NOT NULL,
    ACTION                VARCHAR2(10) NOT NULL,


    CONSTRAINT audit_pk PRIMARY KEY (ID)
);

CREATE INDEX HST_IND_ISIN ON HISTORY(ISIN);