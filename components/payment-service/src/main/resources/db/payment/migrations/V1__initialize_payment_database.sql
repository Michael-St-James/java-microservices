create table revinfo (
    rev integer not null auto_increment,
    revtstmp bigint,
    primary key (rev)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

create table payments (
    id bigint not null auto_increment,
    uuid varchar(36) not null,
    order_uuid varchar(36) not null,
    status ENUM('SUBMITTED', 'PENDING', 'COMPLETED', 'FAILED') not null,
    primary key (id),
    unique (uuid)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

create table payment_details (
    id bigint not null auto_increment,
    payment_id bigint,
    total_amount decimal(19,2),
    currency varchar(3),
    primary key (id),
    foreign key (payment_id) references payments(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
