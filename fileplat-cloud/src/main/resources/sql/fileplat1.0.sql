create table permission
(
  id          varchar(32)            not null comment '权限ID'
    primary key,
  name        varchar(100)           null comment '权限名称',
  url         varchar(100)           null comment '链接',
  type        varchar(1) default '1' not null comment '类型
1 按钮',
  description varchar(300)           null comment '描述'
);

create table role
(
  ID          varchar(32)  not null comment 'id'
    primary key,
  NAME        varchar(200) not null comment '姓名',
  UPDATE_TIME datetime     null comment '更新时间',
  CREATE_TIME datetime     null comment '创建时间'
);

create table role_permission
(
  ID            varchar(32) not null
    primary key,
  PERMISSION_ID varchar(32) not null comment '权限ID',
  ROLE_ID       varchar(32) null comment '角色ID'
);

create table spring_session
(
  SESSION_ID            char(36)     not null
    primary key,
  CREATION_TIME         bigint       not null comment '创建时间',
  LAST_ACCESS_TIME      bigint       not null comment '上次访问时间',
  MAX_INACTIVE_INTERVAL int          not null comment '最大误差',
  PRINCIPAL_NAME        varchar(100) null comment '主要名称
'
);

create index SPRING_SESSION_IX1
  on spring_session (LAST_ACCESS_TIME);

create table spring_session_attributes
(
  SESSION_ID      char(36)     not null,
  ATTRIBUTE_NAME  varchar(200) not null,
  ATTRIBUTE_BYTES blob         not null,
  primary key (SESSION_ID, ATTRIBUTE_NAME),
  constraint SPRING_SESSION_ATTRIBUTES_FK
    foreign key (SESSION_ID) references spring_session (SESSION_ID)
      on delete cascade
);

create index SPRING_SESSION_ATTRIBUTES_IX1
  on spring_session_attributes (SESSION_ID);

create table user
(
  ID          varchar(32)  not null comment '用户ID'
    primary key,
  USER_NAME   varchar(200) not null comment '用户名',
  EMAIL       varchar(100) not null comment '用户邮箱',
  PHONE       varchar(20)  not null comment '手机号',
  PASSWORD    varchar(16)  not null comment '用户密码',
  ADDRESS     varchar(300) null comment '用户地址',
  CREATE_TIME datetime     not null comment '创建时间',
  UPDATE_TIME datetime     null comment '更新时间'
);

create table user_role
(
  ID      varchar(32) not null comment 'ID'
    primary key,
  USER_ID varchar(32) not null,
  ROLE_ID varchar(32) not null comment '角色 ID'
);

