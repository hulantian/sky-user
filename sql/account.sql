-- 创建账户体系数据库
drop database if exists db_account;
create database if not exists db_account CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

drop table if exists db_account.t_organization;
create table if not exists db_account.t_organization
(
    id          varchar(32)  not null primary key comment '主键（机构ID）',
    pid         varchar(32)  not null default '' comment '父机构ID',
    name        varchar(128) not null comment '机构名称',
    status      int(1)       not null default 1 comment '机构状态：1：正常，2：停用',
    is_delete   int(1)       not null default 1 comment '是否已删除：1：正常，2：删除',
    create_time timestamp    not null default current_timestamp comment '创建时间',
    update_time timestamp    not null default current_timestamp on update current_timestamp comment '修改时间'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4 comment '组织机构表';

create table if not exists db_account.t_user
(
    id          varchar(32)  not null primary key comment '主键（用户ID）',
    org_id      varchar(32)  not null default '' comment '机构ID',
    root_org_id varchar(32)  not null default '' comment '根机构ID',
    username    varchar(128) not null comment '用户名称',
    password    varchar(256)  not null comment '密码',
    phone       varchar(16)  not null default '' comment '手机号',
    email       varchar(128) not null default '' comment '电子邮箱',
    status      int(1)       not null default 1 comment '用户状态：1：正常，2：停用',
    is_delete   int(1)       not null default 1 comment '是否已删除：1：正常，2：删除',
    create_time timestamp    not null default current_timestamp comment '创建时间',
    update_time timestamp    not null default current_timestamp on update current_timestamp comment '修改时间'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4 comment '用户表';

create table if not exists db_account.t_system_info
(
    id          varchar(32)  not null primary key comment '主键（系统ID）',
    code        varchar(32)  not null default '' comment '系统编码',
    name        varchar(64)  not null default '' comment '系统名称',
    description varchar(128) not null default '' comment '系统描述',
    is_delete   int(1)       not null default 1 comment '是否已删除：1：正常，2：删除',
    create_time timestamp    not null default current_timestamp comment '创建时间',
    update_time timestamp    not null default current_timestamp on update current_timestamp comment '修改时间'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4 comment '系统信息表';

create table if not exists db_account.t_role
(
    id          varchar(32)  not null primary key comment '主键（角色ID）',
    code        varchar(32)  not null default '' comment '角色代码',
    name        varchar(64)  not null default '' comment '角色名称',
    sys_code    varchar(32)  not null default '' comment '角色所在系统编码',
    description varchar(128) not null default '' comment '角色描述',
    is_delete   int(1)       not null default 1 comment '是否已删除：1：正常，2：删除',
    create_time timestamp    not null default current_timestamp comment '创建时间',
    update_time timestamp    not null default current_timestamp on update current_timestamp comment '修改时间'
) comment '角色代码表';

create table if not exists db_account.t_resource
(
    id          varchar(32)  not null primary key comment '资源Id',
    pid         varchar(32)  not null default '' comment '父级资源Id',
    name        varchar(128) not null default '' comment '资源名称',
    url         varchar(256) not null default '' comment '访问地址',
    is_res      varchar(1)   not null default 1 comment '是否为功能模块：1：功能模块，2：功能文件夹',
    description varchar(128) not null default '' comment '描述信息',
    is_delete   int(1)       not null default 1 comment '是否已删除：1：正常，2：删除',
    create_time timestamp    not null default current_timestamp comment '创建时间',
    update_time timestamp    not null default current_timestamp on update current_timestamp comment '修改时间'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4 comment '资源信息（功能模块）表';

create table if not exists db_account.t_user_role
(
    user_id   varchar(32) not null comment '用户id',
    role_code varchar(32) not null comment '角色编码',
    primary key (user_id, role_code)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4 comment '用户角色关系表';

create table if not exists db_account.t_role_resource
(
    role_code varchar(32) not null comment '角色编码',
    res_id    varchar(32) not null comment '资源id',
    primary key (role_code, res_id)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4 comment '角色资源关系表';