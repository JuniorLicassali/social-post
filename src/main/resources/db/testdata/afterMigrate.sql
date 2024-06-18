set foreign_key_checks = 0;

delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from usuario;

set foreign_key_checks = 1;

alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table usuario auto_increment = 1;

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_POSTAGEM', 'Permite consultar postagem');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_POSTAGEM', 'Permite editar postagem');

insert into grupo (id, nome) values (1, 'Administrador'), (2, 'Usuario');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2); 

