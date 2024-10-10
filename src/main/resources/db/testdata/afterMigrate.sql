set foreign_key_checks = 0;

delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from usuario;
delete from postagem;
delete from comentario;
delete from postagem_comentario;
delete from foto_postagem;
delete from oauth_client_details;

set foreign_key_checks = 1;

alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table usuario auto_increment = 1;
alter table postagem auto_increment = 1;
alter table comentario auto_increment = 1;
alter table postagem_comentario auto_increment = 1;

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_POSTAGEM', 'Permite consultar postagem');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_POSTAGEM', 'Permite editar postagem');
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários');
insert into permissao (id, nome, descricao) values (4, 'EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite criar ou editar usuários');

insert into grupo (id, nome) values (1, 'Administrador'), (2, 'Usuario');

insert into usuario (nome, email, senha, data_cadastro) values ('Sebastiao da Galera', 'email@example.com', '$2a$12$65VgccevndwXJMg0lVWCx.ftbya3d65XbyVGtWLL9FX1u.Xm49R/e', '2024-06-03 02:00:30');
insert into usuario (nome, email, senha, data_cadastro) values ('Sean Strickland', 'sean@example.com', '$2a$12$65VgccevndwXJMg0lVWCx.ftbya3d65XbyVGtWLL9FX1u.Xm49R/e', '2024-06-03 02:00:30');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2); 

insert into postagem (codigo, data_postagem, descricao, autor_id) values ('10e49ddf-8f2f-487b-a9cf-1e79335685b0', '2019-11-03 02:00:30', 'Dólar em alta, sobe para 4,20', 1);

insert into comentario (id, texto, data_comentario, usuario_id) values (1, "1, 2, 3 testando", '2024-06-03 02:00:30', 1);

insert into postagem_comentario (postagem_id, comentario_id) values (1, 1);

insert into oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'social-web', null, '$2a$12$KsWZ6dnTnT/pwmVBtZopPu93hdBMOxBOlvfYRdSvkugsVaBJQLYZa',
  'READ,WRITE', 'password', null, null,
  60 * 60 * 6, 60 * 24 * 60 * 60, null
);

insert into oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'analytics-example', null, '$2a$12$KsWZ6dnTnT/pwmVBtZopPu93hdBMOxBOlvfYRdSvkugsVaBJQLYZa',
  'READ,WRITE', 'authorization_code', 'http://aplicacao-cliente', null,
  null, null, null
);

insert into oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  '("estatisticas")', null, '$2a$12$KsWZ6dnTnT/pwmVBtZopPu93hdBMOxBOlvfYRdSvkugsVaBJQLYZa',
  'READ,WRITE', 'client_credentials', null, 'CONSULTAR_PEDIDOS,GERAR_RELATORIOS',
  null, null, null
);