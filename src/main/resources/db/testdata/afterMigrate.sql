set foreign_key_checks = 0;

delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from usuario;
delete from postagem;
delete from comentario;
delete from postagem_comentario;
delete from foto_postagem;
delete from oauth2_registered_client;

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


INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('1', 'socialpost-backend', '2024-10-19 22:18:57', '$2a$12$xynnsPaCuu4mO3vhbC4jq.mO15Eha.tm5Y89SSbKKhu.KG72P2VZK', NULL, 'SocialPost Backend', 'client_secret_basic', 'client_credentials', '', 'READ', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('2', 'socialpostweb', '2024-10-19 22:18:58', '$2a$12$qdd1cFflV1NOuEUm2.krs.jiEdTe9UZGzobTLNPfSYsNPAeTI4f8C', NULL, 'SocialPost Web', 'client_secret_basic', 'refresh_token,authorization_code', 'http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html,http://127.0.0.1:8080/authorized', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",900.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('3', 'socialpostanalytics', '2024-10-19 22:18:58', '$2a$12$qdd1cFflV1NOuEUm2.krs.jiEdTe9UZGzobTLNPfSYsNPAeTI4f8C', NULL, 'SocialPost Analytics', 'client_secret_basic', 'authorization_code', 'http://www.foodanalytics.local:8082', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

