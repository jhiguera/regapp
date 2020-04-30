insert into ROLE (NAME) values('ADMIN');
insert into ROLE (NAME) values('USER');
insert into USER(username,password,name) values('jhiguera','$2a$10$FMQOTEUiRN1L2MV2gfYas.MEDnLcEffuenRme5WdFgkwcuWA2jyhG','Jaime Higuera');
insert into USER_ROLES(user_id,role_id) values(1,1);
