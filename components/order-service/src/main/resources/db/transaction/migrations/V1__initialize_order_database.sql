create schema if not exists TRANSACTION;

create table TRANSACTION.revinfo (
    rev integer not null auto_increment,
    revtstmp bigint,
    primary key (rev)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

create table TRANSACTION.orders (
    id bigint not null auto_increment,
    uuid varchar(36) not null,
    status ENUM('SUBMITTED', 'PAYMENT_PENDING', 'PAYMENT_COMPLETED', 'SHIPPED', 'COMPLETED') not null,
    primary key (id),
    unique (uuid)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
