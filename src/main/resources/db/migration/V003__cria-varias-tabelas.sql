create table postagem (
	id bigint not null auto_increment,
    data_postagem datetime not null,
    descricao text not null,
    
    primary key (id)
)engine=InnoDB default charset=utf8;

create table comentario (
	id bigint not null auto_increment,
    texto text not null,
    data_comentario datetime not null,
    usuario_id bigint not null,
    
    primary key (id)
) engine=InnoDB default charset=utf8;

create table postagem_comentario(
	postagem_id bigint not null,
    comentario_id bigint not null,
    primary key (postagem_id, comentario_id)
) engine=InnoDB default charset=utf8;

alter table comentario add constraint fk_comentario_usuario_id 
foreign key (usuario_id) references usuario (id);

alter table postagem_comentario add constraint fk_postagem_comentario_postagem_id 
foreign key (postagem_id) references postagem(id);

alter table postagem_comentario add constraint fk_postagem_comentario_comentario_id 
foreign key (comentario_id) references comentario(id);