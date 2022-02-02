create table tb_order (
    id bigint auto_increment,
    name varchar(100) not null,
    product bigint not null,
    total decimal(18,2) not null,
    purchase_date timestamp not null,
    cpf_client varchar(20) not null,
    cep varchar(9) not null,

    primary key (id)
);
