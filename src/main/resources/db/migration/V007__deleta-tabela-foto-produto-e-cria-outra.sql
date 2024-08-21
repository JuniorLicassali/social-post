drop table if exists foto_postagem;

create table foto_postagem (
    postagem_id bigint not null,
    nome_arquivo varchar(150) not null,
    content_type varchar(80) not null,
    tamanho bigint not null,


    primary key (postagem_id), constraint fk_foto_postagem_postagem foreign key (postagem_id) references postagem (id)
) engine=InnoDB default charset=utf8;