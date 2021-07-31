create table if not exists app_users  (id bigint not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), status integer not null, username varchar(255), primary key (id));
create table if not exists  roles (role_id integer not null, role varchar(255), primary key (role_id));
create table if not exists  user_role (user_id bigint not null, role_id integer not null, primary key (user_id, role_id));
create sequence hibernate_sequence start with 1 increment by 1;
alter table user_role add constraint FKt7e7djp752sqn6w22i6ocqy6q foreign key (role_id) references roles;
alter table user_role add constraint FKnnjwin2r8oajs3wmc8sbn0672 foreign key (user_id) references app_users;
-- CREATE TABLE IF NOT EXISTS `roles` (
--     role_id INT AUTO_INCREMENT,
--     role VARCHAR(255)
-- );

INSERT INTO  roles  (role_id, role) VALUES (1, 'ADMIN'),(2, 'EDITOR');
INSERT INTO app_users(id, email, first_name, last_name, password, status, username)
values ( 2,'kwamekert@gmail.com','kwame','asante',
        '$2a$10$dD.CUVm1H/LdDG0KHDRb5OW/0MOQAEtaREhWFrwbX.VAu5lSEvaY2',1,'kwamekert');
INSERT INTO  user_role(user_id, role_id) VALUES ( 2,1 );