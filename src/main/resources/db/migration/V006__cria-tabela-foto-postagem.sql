create table foto_postagem (
    id bigint not null auto_increment primary key,
    postagem_id bigint not null,
    nome_arquivo varchar(150) not null,
    content_type varchar(80) not null,
    tamanho bigint not null,
    foreign key (postagem_id) references postagem(id) on delete cascade
) engine=InnoDB default charset=utf8;