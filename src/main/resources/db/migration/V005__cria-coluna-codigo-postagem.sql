alter table postagem add codigo varchar(36) not null after id;
update postagem set codigo = uuid();
alter table postagem add constraint uk_postagem_codigo unique (codigo);