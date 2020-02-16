-- liquibase formatted sql

-- changeset admin:create_audit dbms:h2
create table AUDIT
(
    ID                    NUMBER(10, 0) NOT NULL,
    ISIN                  VARCHAR2(12) NOT NULL,
    BID                   NUMBER(10, 2) NOT NULL,
    ASK                   NUMBER(10, 2) NOT NULL,
    ELVL                  NUMBER(10, 2) NOT NULL,
    CREATE_TIME           TIMESTAMP(6)  NOT NULL,
    ACTION                VARCHAR2(10) NOT NULL,


    CONSTRAINT audit_pk PRIMARY KEY (ID)
);
