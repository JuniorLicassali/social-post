CREATE TABLE usuario_post (
    usuario_id BIGINT NOT NULL,
    postagem_id BIGINT NOT NULL,
    
    primary key (postagem_id, usuario_id)
) engine=InnoDB default charset=utf8;

alter table usuario_post add constraint fk_usuario_post_usuario foreign key (usuario_id) references usuario (id);
alter table usuario_post add constraint fk_usuario_post_postagem foreign key (postagem_id) references postagem (id);