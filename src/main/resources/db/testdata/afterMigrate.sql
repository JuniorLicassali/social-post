set foreign_key_checks = 0;

delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from usuario;
delete from postagem;
delete from comentario;
delete from postagem_comentario;

set foreign_key_checks = 1;

alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table usuario auto_increment = 1;
alter table postagem auto_increment = 1;
alter table comentario auto_increment = 1;
alter table postagem_comentario auto_increment = 1;

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_POSTAGEM', 'Permite consultar postagem');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_POSTAGEM', 'Permite editar postagem');

insert into grupo (id, nome) values (1, 'Administrador'), (2, 'Usuario');

insert into usuario (nome, email, senha, data_cadastro) values ('Sebastiao da Galera', 'email@example.com', '123', '2024-06-03 02:00:30');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2); 

insert into postagem (data_postagem, descricao, autor_id) values ('2019-11-03 02:00:30', 'Dólar em alta, sobe para 4,20', 1);

insert into comentario (id, texto, data_comentario, usuario_id) values (1, "1, 2, 3 testando", '2024-06-03 02:00:30', 1);

insert into postagem_comentario (postagem_id, comentario_id) values (1, 1);