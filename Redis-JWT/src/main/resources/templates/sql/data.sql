insert into MY_USER (user_id,name, password, nickname, check_val) values (1,'관리자', '$2a$12$FW6PENVZ78flay0Haevz1Oekx2cL9tx27xwe3rgJ3MZuV/oEF3qua', 'admin', 1);
insert into MY_USER (user_id,name, password, nickname, check_val) values (2,'뷔', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

insert into authority(authority_id,authority_name) values(1,'ROLE_USER');
insert into authority(authority_id,authority_name) values(2,'ROLE_ADMIN');

insert into user_authority values(1,1);
insert into user_authority values(1,2);
insert into user_authority values (2, 2);