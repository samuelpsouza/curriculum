create table matrix
(
  id bigserial not null
    constraint matrix_pkey
      primary key
);

alter table matrix
  owner to postgres;

create table major
(
  id                  bigserial    not null
    constraint major_pkey
      primary key,
  code                varchar(255) not null
    constraint uk_pme78hquxj4fio2f880b2gaa9
      unique,
  description         text,
  duration            varchar(255),
  period              varchar(255),
  registration_number varchar(255),
  title               varchar(255) not null,
  matrix_id           bigint
    constraint fkmuth3iturexhid81pjcs7jts
      references matrix
);

alter table major
  owner to postgres;

create table role
(
  id   serial not null
    constraint role_pkey
      primary key,
  name varchar(255)
);

alter table role
  owner to postgres;

create table semester
(
  id          bigserial    not null
    constraint semester_pkey
      primary key,
  description varchar(255) not null
);

alter table semester
  owner to postgres;

create table course
(
  id          bigserial    not null
    constraint course_pkey
      primary key,
  code        varchar(255) not null
    constraint uk_i60mruj0y7a7vs99dqpiye7en
      unique,
  description varchar(255) not null,
  semester_id bigint
    constraint fklmyb73uymsfhqh374ndr3n4c0
      references semester
);

alter table course
  owner to postgres;

create table matrix_course_list
(
  matrix_id      bigint not null
    constraint fk7ylj6naigbde6mftqwj85ys7n
      references matrix,
  course_list_id bigint not null
    constraint uk_r2685derhnyedknixfqjt20tr
      unique
    constraint fktbytk86yjask8ayxa1m0kar98
      references course
);

alter table matrix_course_list
  owner to postgres;

create table matrix_semester_list
(
  matrix_id        bigint not null
    constraint fknwwmkb0oo576fp9kdlerb44rr
      references matrix,
  semester_list_id bigint not null
    constraint uk_ghivreeuu7q7hmlmsinfma564
      unique
    constraint fkekrkmmfmlqly3len22k5g0mtc
      references semester
);

alter table matrix_semester_list
  owner to postgres;

create table users
(
  id       bigserial    not null
    constraint users_pkey
      primary key,
  password varchar(255) not null,
  username varchar(255) not null
);

alter table users
  owner to postgres;

create table users_role_list
(
  user_id      bigint  not null
    constraint fk2oga5218nq5f1133098iwq6r5
      references users,
  role_list_id integer not null
    constraint fk6i3vy3o43ojqp827igbjxenyb
      references role
);

alter table users_role_list
  owner to postgres;

