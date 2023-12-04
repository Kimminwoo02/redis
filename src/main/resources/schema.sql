drop table store;
create table store (
        id number(19,0) not null,
        latitude float(53) not null,
        longitude float(53) not null,
        store_name varchar2(255),
        store_address varchar2(255),
        distance float(53),
        primary key (id)
    );